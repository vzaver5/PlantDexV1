package com.example.plantdexv1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.navigation.fragment.NavHostFragment;


public class FirstFragment extends Fragment{
    AutoCompleteTextView plantNameField;
    final String token = "r9Ft9FXCoFva7YmQ1W93N5J8wRck-fgxJnUyFZXV15k";
    String plantName = null;
    String search = "plants/search";
    String get_url="";
    HttpRequest retInfo = new HttpRequest();
    int pageNumber = 1;
    int maxPageNumber = -2;
    ListView listView;
    //For thread
    private Handler uiUpdater = null;
    private static final String plantListKey = "plantListKey";
    // Child thread sent message type value to activity main thread Handler.
    private static final int REQUEST_CODE_SHOW_RESPONSE_TEXT = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        //this to set delegate/listener back to this class
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.Profile) {
            System.out.println("FirstFrag to Profile selected");
            getParentFragmentManager().executePendingTransactions();
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.firstFragment_to_profile_fragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @SuppressLint("HandlerLeak")
    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("back in first fragment");
        //Retrieve the plant name that was searched
        plantNameField = view.findViewById(R.id.search_field);

        //Hide the previous and next buttons
        view.findViewById(R.id.prev_in_list).setVisibility(View.GONE);
        view.findViewById(R.id.next_in_list).setVisibility(View.GONE);

        //Handle information coming from the child thread
        //Child thread is the one handling the GET request
        if(uiUpdater == null) {
            System.out.println("uiUpdater is null");
            uiUpdater = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what == REQUEST_CODE_SHOW_RESPONSE_TEXT)
                    {
                        Bundle bundle = msg.getData();
                        if(bundle != null)
                        {
                            final String responseText = bundle.getString(plantListKey);
                            System.out.println("Received from the HTTPReq:" + responseText);
                            //Array of string(com/sci/id) for adapter
                            String[] resultingList = parseJSONForPlantName(responseText, pageNumber);

                            //User had a garbage search criterion
                            if(resultingList.length == 0){
                                Toast.makeText(getContext(), "No matches to the search criterion", Toast.LENGTH_LONG).show();
                            }
                            if(resultingList.length == 20){
                                //Previous and next buttons appear
                                view.findViewById(R.id.prev_in_list).setVisibility(View.VISIBLE);
                                view.findViewById(R.id.next_in_list).setVisibility(View.VISIBLE);
                            }


                            ArrayAdapter<String> adapter  = new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_list_item_1, resultingList);

                            listView = (ListView) view.findViewById(R.id.listview);
                            listView.setAdapter(adapter);


                            System.out.println("Frag should be updated");

                            AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView parent, View v, int position, long id) {
                                    System.out.println("speci clicked");
                                    int selectedPlantId = parseJSONForPlantId(responseText, (int) id);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("plantId",selectedPlantId);
                                    //Move to new fragment with the id of the selected plant
                                    NavHostFragment.findNavController(FirstFragment.this)
                                            .navigate(R.id.firstFragment_to_specificFragment,bundle);
                                }
                            };
                            listView.setOnItemClickListener(messageClickedHandler);
                          }
                    }
                }
            };
        }

        //Search for the plant
        view.findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Hide keyboard
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                //Get plant name and format it
                plantName = plantNameField.getText().toString();
                plantName = plantName.replaceAll("\\s","_");

                if(!TextUtils.isEmpty(plantName)){  //Make sure plant was not left empty
                    searchPlantName(plantName);
                }else{
                    Toast.makeText(getContext(), "Field cannot be left empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Go to the next page of the list (if any)
        view.findViewById(R.id.next_in_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(plantName)){
                    Toast.makeText(getContext(), "Field cannot be left empty", Toast.LENGTH_SHORT).show();
                }else if(pageNumber < maxPageNumber){
                    System.out.println("Going to next page");
                    pageNumber++;
                    searchPlantName(plantName);
                }else{
                    Toast.makeText(getContext(), "There are no more plants that match your search", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Go to the previous page of the list (if any)
        view.findViewById(R.id.prev_in_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(plantName)){
                    Toast.makeText(getContext(), "Field cannot be left empty", Toast.LENGTH_SHORT).show();
                }else if(pageNumber > 1){
                    System.out.println("Going back one page");
                    pageNumber--;
                    searchPlantName(plantName);
                }else{
                    Toast.makeText(getContext(), "You are on the first page", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("here");
        listView = (ListView) getView().findViewById(R.id.listview);
    }

    // Start a thread to send http request to web server use HttpURLConnection object.
    private void startSendHttpRequestThread(final String url)
    {
        Thread sendHttpRequestThread = new Thread() {
            @Override
            public void run() {
                System.out.println("Starting child thread");
                HttpURLConnection con = null;
                BufferedReader in = null;
                System.out.println("Executing GET Request");
                try {
                    URL obj = new URL(url);
                    con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    // Set connection timeout and read timeout value.
                    con.setConnectTimeout(10000);
                    con.setReadTimeout(10000);

                    //Find max number of pages in list so you dont go over it
                    int numberOfPages = con.getHeaderFieldInt("total-pages", 151);
                    maxPageNumber = numberOfPages;

                    int responseCode = con.getResponseCode();
                    System.out.println("GET Response Code :: " + responseCode);
                    if (responseCode == HttpURLConnection.HTTP_OK) { // success 200
                        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String inputLine = "";
                        StringBuffer response = new StringBuffer();
                        while (true) {
                            if (!((inputLine = in.readLine()) != null)) break;
                            System.out.println("appending");
                            response.append(inputLine);
                        }

                        //Create message
                        Message message = new Message();
                        // Set message type.
                        message.what = REQUEST_CODE_SHOW_RESPONSE_TEXT;
                        Bundle bundle = new Bundle();
                        bundle.putString(plantListKey, response.toString());
                        message.setData(bundle);
                        // Send message to main thread Handler to process.
                        uiUpdater.sendMessage(message);

                    } else {
                        Toast.makeText(getContext(), "GET request failed", Toast.LENGTH_SHORT).show();
                        System.out.println("GET request failed");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Check thy internet cnxn");
                    e.printStackTrace();
                } finally {
                    try {
                        if (in != null) {
                            in.close();
                        }
                        if (con != null) {
                            con.disconnect();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };  //End of thread specification
        // Start the child thread to request web page.
        sendHttpRequestThread.start();
    }

    private void searchPlantName(String plantName){
        System.out.println("----------Searched for: "+ plantName);
        get_url = "https://trefle.io/api/v1/"+ search +"?token="+token+"&q="+plantName+"&page=" + pageNumber;
        System.out.println(get_url);
        startSendHttpRequestThread(get_url);    //Create child thread to do GET request
    }

    //Purpose: Return a String[] to send to ListView for display
    static String[] parseJSONForPlantName(String jsonString, int pageNumber){
        pageNumber = pageNumber-1;
        Gson gson= new Gson();
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("data");
        String[] returnArr = new String[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            //Receive each plant
            Plants plants = gson.fromJson(jsonArray.get(i), Plants.class);
            System.out.println("Plant sci name: " + plants.getScientific_name());
            System.out.println("Plant id: " + plants.getId());
            System.out.println("Plant com name: " + plants.getCommon_name());
            System.out.println("Plant images: " + plants.getImages());
            //System.out.println("Plant main species: " + plants.getMain_species());
            //Print sci name and common name of the plant
            if(plants.getCommon_name() == null){
                returnArr[i] = "\n" + (20*pageNumber+i+1) + ". " + plants.getScientific_name();
            }else{
                returnArr[i] = "\n" + (20*pageNumber+i+1) + ". " + plants.getCommon_name() + " / " + plants.getScientific_name();
            }
        }
        return returnArr;
    }

    //Purpose: Method to return PlantId so that we can do direct look up when a plant is selected
    static int parseJSONForPlantId(String jsonString, int position){
        Gson gson= new Gson();
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("data");
        Plants selectedPlant = gson.fromJson(jsonArray.get(position), Plants.class);
        int plantId = selectedPlant.getId();
        System.out.println("The plant's id is:" + plantId);
        return plantId;
    }
}