package com.example.plantdexv1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import com.google.gson.JsonElement;

public class SearchPlant extends Fragment {
    String get_url = "";
    String token = "r9Ft9FXCoFva7YmQ1W93N5J8wRck-fgxJnUyFZXV15k";
    private static final int REQUEST_CODE_SHOW_RESPONSE_TEXT = 1;
    private Handler uiUpdater = null;
    private static final String plantInfoKey = "plantInfoKey";
    private DatabaseReference userDb;
    String comName;
    String sciName;
    User user;

    //Views
    TextView sciTextView;
    TextView comTextView;
    TextView flowerColorTV;
    TextView flowerConsTV;
    TextView foliageColorTV;
    TextView foliageTextureTV;
    TextView fruitSeedColorTV;
    TextView fruitSeedConsTV;
    TextView fruitSeedSeedPersistenceTV;
    TextView growthPhRangeTV;
    TextView precipRangeMMTV;
    TextView tempMinCTV;
    TextView speciGrowthFormTV;
    TextView speciGrowthHabitTV;
    TextView speciGrowthRateTV;
    TextView speciGrowthShapeTV;

    ImageView imgV;
    Button addToGarden;
    int currentPic = 0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userDb = FirebaseDatabase.getInstance().getReference().child("user:" + userId);
        //userDb = FirebaseDatabase.getInstance().getReference().child("user:" + userId).child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_result_from_search, container, false);
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
            System.out.println("SearchPlant to Profile selected");
            NavHostFragment.findNavController(SearchPlant.this)
                    .navigate(R.id.specific_fragment_to_profile_fragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressLint("HandlerLeak")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        int selectedPlantId = bundle.getInt("plantId");


        if(uiUpdater == null) {
            uiUpdater = new Handler()
            {
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what == REQUEST_CODE_SHOW_RESPONSE_TEXT)
                    {
                        Bundle bundle = msg.getData();
                        if(bundle != null)
                        {
                            String responseText = bundle.getString(plantInfoKey);

                            //Create plant from the responseText once
                            //To be used to get all the info we need
                            Gson gson = new Gson();
                            JsonObject jsonObject = JsonParser.parseString(responseText).getAsJsonObject();

                            Plants plant = gson.fromJson(jsonObject.get("data"), Plants.class);

                            System.out.println("Response: " + responseText);
                            System.out.println("Plant sci name: " + plant.getScientific_name());
                            System.out.println("Plant id: " + plant.getId());
                            System.out.println("Plant com name: " + plant.getCommon_name());
                            System.out.println("Plant images: " + plant.getImages());

                            //Below's variables are laid out in java/res/layout/activity_results_from_search.xml
                            //Then you assign values. And they will show in those locations described in path above.
                            //Get the plant specifics
                            sciTextView = (TextView) getView().findViewById(R.id.sciNameFill);
                            comTextView = (TextView) getView().findViewById(R.id.commNameFill);
                            //Flower info
                            flowerColorTV = (TextView) getView().findViewById(R.id.flowerColorFill);
                            flowerConsTV = (TextView) getView().findViewById(R.id.flowerConsFill);
                             //Foliage info
                            foliageColorTV = (TextView) getView().findViewById(R.id.foliageColorFill);
                            foliageTextureTV = (TextView) getView().findViewById(R.id.foliageTextureFill);
                            //FruitSeed info
                            fruitSeedColorTV = (TextView) getView().findViewById(R.id.fruitSeedColorFill);
                            fruitSeedConsTV = (TextView) getView().findViewById(R.id.fruitSeedConsFill);
                            fruitSeedSeedPersistenceTV = (TextView) getView().findViewById(R.id.fruitSeedSeedPersistenceFill);
                            //Growth info
                            growthPhRangeTV = (TextView) getView().findViewById(R.id.growthPhRangeFill);
                            precipRangeMMTV = (TextView) getView().findViewById(R.id.precipRangeFill);
                            tempMinCTV = (TextView) getView().findViewById(R.id.tempMinCFFill);
                            //Specification info
                            speciGrowthFormTV = (TextView) getView().findViewById(R.id.specificationGrowthFormFill);
                            speciGrowthHabitTV = (TextView) getView().findViewById(R.id.specificationGrowthHabitFill);
                            speciGrowthRateTV = (TextView) getView().findViewById(R.id.specificationGrowthRateFill);
                            speciGrowthShapeTV = (TextView) getView().findViewById(R.id.specificationShapeOriFill);
                            //Pictures
                            imgV = (ImageView) getView().findViewById(R.id.plantImage);
                            //Add to garden
                            addToGarden = (Button) getView().findViewById(R.id.addToGarden);

                            //Assign Scientific Name
                            sciName = parseJsonSciName(plant);
                            if(sciName != ""){
                                sciTextView.setText(sciName);
                            }else{
                                sciTextView.setText("No Scientific Name Given");
                            }

                            //Assign Common Name
                            comName = parseJsonComName(plant);
                            if(comName != ""){
                                //insert into fill
                                comTextView.setText(comName);
                            }else{
                                comTextView.setText("No Common Name Given");
                            }

                            //Assign function addToGarden to the Add To Garden button
                            //Add plant to userDb
                            addToGarden.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Get the current user info from db
                                    //updateUserInfo();
                                    if(!comName.equals("")){
                                        System.out.println("Common name available");
                                        String pn = comName + " , " + sciName;
                                        userDb.child("virtualGarden").child(pn).setValue(true);     //add to user db
                                    }else{
                                        System.out.println("Common name not available");
                                        String pn = sciName;
                                        userDb.child("virtualGarden").child(pn).setValue(true);     //add to user db
                                    }
                                    Toast.makeText(getContext(), "Added to your Garden!", Toast.LENGTH_SHORT).show();
                                }
                            });
                            //TODO
                            //If the plant is already in your virtual Garden db
                            //Then the "Add To Garden Button" should say "Added to Garden"
                                //Idea: When loading the page
                                    //Read from userDb and check if plant is child of Virtual Garden
                                    //If yes : "Added to Garden"
                                    //If no  : "Add to Garden"

                            //TODO:
                                //Add a drop down for the different picture types available found in main_species->images
                                //Images is an array
                                //The drop down content will be each index of the array
                                //Then, the images will load from that index's array.
                                //Make sure it remains clickable and rotates between the pics

                            //Assign pictures
                            final String plantUrl = plant.getImages();
                            Picasso.get().load(plantUrl).into(imgV);
                            /*
                            final String[] plantUrls = parseJsonPlantPic(plant);
                            if(plantUrls.length != 0){
                                //Display into plantImage
                                //Load the first image up
                                Picasso.get().load(plantUrls[currentPic]).into(imgV);

                                //Clicking on the picture will iterate through the pictures
                                imgV.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        System.out.println("You clicked the image");
                                        //Be able to rotate through the pictures while
                                        //You still are iterating thru the array
                                        if((currentPic+1) < plantUrls.length){
                                            currentPic++;
                                            System.out.println("count after click:" + currentPic);
                                            Picasso.get().load(plantUrls[currentPic]).into(imgV);
                                        }else{
                                            System.out.println("restarting count");
                                            currentPic = 0;
                                            Picasso.get().load(plantUrls[currentPic]).into(imgV);
                                        }
                                    }
                                });

                            }
                            */

                            //Assign Flower Color
                            if(plant.getMain_species().getFlower().getColor() != null) {
                                flowerColorTV.append(plant.getMain_species().getFlower().getColor());
                            } else {
                                flowerColorTV.append("No info");
                            }

                            //Assign Flower Con
                            if(plant.getMain_species().getFlower().isConspicuous()) {
                                flowerConsTV.append("True");
                            } else {
                                flowerConsTV.append("False");
                            }
                            //Assign Foliage Color
                            if(plant.getMain_species().getFoliage().getColor() != null){
                                foliageColorTV.append(plant.getMain_species().getFoliage().getColor());
                            }else{
                                foliageColorTV.append("No info");
                            }

                            //Assign Foliage Texture
                            if(plant.getMain_species().getFoliage().getTexture() != null) {
                                foliageTextureTV.append(plant.getMain_species().getFoliage().getTexture());
                            } else {
                                foliageTextureTV.append("No info");
                            }

                            //Assign FruitSeed Color
                            if(plant.getMain_species().getFruit_or_seed().getColor() != null){
                                fruitSeedColorTV.append(plant.getMain_species().getFruit_or_seed().getColor());
                            }else{
                                fruitSeedColorTV.append("No info");
                            }

                            //Assign FruitSeed Cons
                            if(plant.getMain_species().getFruit_or_seed().isConspicuous()){
                                fruitSeedConsTV.append("True");
                            }else{
                                fruitSeedConsTV.append("False");
                            }

                            //Assign FruitSeed Seed Persistence
                            if(plant.getMain_species().getFruit_or_seed().isSeed_persistence()){
                                fruitSeedSeedPersistenceTV.append("True");
                            }else{
                                fruitSeedSeedPersistenceTV.append("False");
                            }

                            growthPhRangeTV.append(plant.getMain_species().getGrowth().getPh_minimum() + " to " +
                                    plant.getMain_species().getGrowth().getPh_maximum());
                            //Assign Growth PH Range
//                            if(plant.getMain_species().getGrowth().getPh_minimum() != null ||
//                                    plant.getMain_species().getGrowth().getPh_maximum() != null){
//                                growthPhRangeTV.append(plant.getMain_species().getGrowth().getPh_minimum() + " to " +
//                                    plant.getMain_species().getGrowth().getPh_maximum() + "");
//                            }else{
//                                growthPhRangeTV.append("No info");
//                            }

                            precipRangeMMTV.append(plant.getMain_species().getGrowth().getMinimum_precipitation().getMm() + "mm to " +
                                    plant.getMain_species().getGrowth().getMaximum_precipitation().getMm() + "mm");
                            //Assign Precipitation Range MM
//                            if(plant.getMain_species().getGrowth().getPrecipitation_minimum().getInches() != null ||
//                                    plant.getMain_species().getGrowth().getPrecipitation_maximum().getInches() != null){
//                                precipMaxInchesTV.append(plant.getMain_species().getGrowth().getPrecipitation_minimum().getInches() + " to " +
//                                    plant.getMain_species().getGrowth().getPrecipitation_maximum().getInches() + "");
//                            }else{
//                                precipMaxInchesTV.append("No info");
//                            }

                            tempMinCTV.append(plant.getMain_species().getGrowth().getMinimum_temperature().getDeg_c() + "C / " +
                                    plant.getMain_species().getGrowth().getMinimum_temperature().getDeg_f() + "F");
                            //Assign Temp Min C and F
//                            if(plant.getMain_species().getGrowth().getTemperature_minimum().getDeg_c() != null ||
//                                    plant.getMain_species().getGrowth().getTemperature_minimum().getDeg_f() != null){
//                                tempMinCTV.append(plant.getMain_species().getGrowth().getTemperature_minimum().getDeg_c() + "C / " +
//                                    plant.getMain_species().getGrowth().getTemperature_minimum().getDeg_f() + "F");
//                            }else{
//                                tempMinCTV.append("No info");
//                            }

                            //Assign Specification Growth Form
                            if(plant.getMain_species().getSpecifications().getGrowth_form() != null){
                                speciGrowthFormTV.append(plant.getMain_species().getSpecifications().getGrowth_form());
                            }else{
                                speciGrowthFormTV.append("No info");
                            }
                            //Assign Specification Growth Habit
                            if(plant.getMain_species().getSpecifications().getGrowth_habit() != null){
                                speciGrowthHabitTV.append(plant.getMain_species().getSpecifications().getGrowth_habit());
                            }else{
                                speciGrowthHabitTV.append("No info");
                            }
                            //Assign Specification Growth Rate
                            if(plant.getMain_species().getSpecifications().getGrowth_rate() != null){
                                speciGrowthRateTV.append(plant.getMain_species().getSpecifications().getGrowth_rate());
                            }else{
                                speciGrowthRateTV.append("No info");
                            }
                            //Assign Specification Shape Ori
                            if(plant.getMain_species().getSpecifications().getShape_and_orientation() != null){
                                speciGrowthShapeTV.append(plant.getMain_species().getSpecifications().getShape_and_orientation());
                            }else{
                                speciGrowthShapeTV.append("No info");
                            }
                        }
                    }
                }
            };
        }
        searchPlantInfo(selectedPlantId);
    }

    public void onStart(){
        super.onStart();
    }

    //Call child thread
    private void searchPlantInfo(int plantId){
        System.out.println("----------Plant Id: "+ plantId);
        get_url = "https://trefle.io/api/v1/plants/"+ plantId +"?token="+token;
        //System.out.println(get_url);
        getSpecificPlantInfo(get_url);    //Create child thread to do GET request
    }

    // Start a thread to send http request to web server use HttpURLConnection object.
    private void getSpecificPlantInfo(final String url)
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
                        bundle.putString(plantInfoKey, response.toString());
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

    //Return: String that has the plant's scientific name
    static String parseJsonSciName(Plants plant){
        String response = "";
        if(plant.getScientific_name() != null){
            response = response + plant.getScientific_name() + " ";
        }
        return response;
    }

    //Return: String that has the plant's common name
    static String parseJsonComName(Plants plant){

        String response = "";
        if(plant.getCommon_name() != null){
            response = response + plant.getCommon_name();
        }
        return response;
    }

    //Return: String array that has urls of pics of the plant
    static String[] parseJsonPlantPic(Plants plant){
        //String[] response = new String[plant.getImages().length];
        String[] response = {""};
        //Confirm that we have urls
        /*if(plant.getImages().length != 0) {
            for(int i = 0; i < plant.getImages().length; i++) {
                response[i] = plant.getImages()[i].getUrl();
            }
        }
         */
        return response;
    }

    public void updateUserInfo(){
        System.out.println("Retrieving the vG info");
        userDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                //Im not entering here anymore
                System.out.println("-------- I AM IN UPDATE USER INFO");
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String friend = ds.getKey();
                    user.addToVirtualGarden(friend);    //add to the user object's virtual garden
                }
                //System.out.println(user.getName());
                //System.out.println(user.getUserId());
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

}

