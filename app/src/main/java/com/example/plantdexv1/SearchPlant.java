package com.example.plantdexv1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SearchPlant extends Fragment {
    String get_url = "";
    String token = "T2F6bmtvTG8rSjBNcmhIVnBhUmVZQT09";
    private static final int REQUEST_CODE_SHOW_RESPONSE_TEXT = 1;
    private Handler uiUpdater = null;
    private static final String plantInfoKey = "plantInfoKey";
    TextView sciTextView;
    TextView comTextView;
    TextView flowerColorTV;
    TextView flowerConsTV;
    TextView foliageColorTV;
    TextView foliagePorositySummerTV;
    TextView foliagePorosityWinterTV;
    TextView foliageTextureTV;
    TextView fruitSeedColorTV;
    TextView fruitSeedConsTV;
    TextView fruitSeedSeedAbundanceTV;
    TextView fruitSeedSeedPeriodBeginTV;
    TextView fruitSeedSeedPeriodEndTV;
    TextView fruitSeedSeedPersistenceTV;
    TextView growthPhMaxTV;
    TextView growthPhMinTV;
    TextView precipMaxInchesTV;
    TextView precipMinInchesTV;
    TextView tempMinCTV;
    TextView tempMinFTV;
    TextView speciGrowthFormTV;
    TextView speciGrowthHabitTV;
    TextView speciGrowthRateTV;
    TextView speciGrowthShapeTV;
    TextView seedBloomPeriodTV;
    TextView seedCommAvTV;
    TextView seedSeedSpreadRateTV;
    TextView seedSeedPerPoundTV;

    ImageView imgV;
    Button addToGarden;
    int currentPic = 0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("view created");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_result_from_search, container, false);
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
                            final String responseText = bundle.getString(plantInfoKey);

                            //Create plant from the responseText once
                            //To be used to get all the info we need
                            Gson gson = new Gson();
                            Plants plant = gson.fromJson(responseText, Plants.class);
                            System.out.println("Response: "+responseText);
                            String complete_data = plant.getMain_species().getComplete_data();
                            if(complete_data.equals("true")){
                                System.out.println("---------- We have complete data on the plant");
                            }

                            //Get the plant specifics
                            sciTextView = (TextView) getView().findViewById(R.id.sciNameFill);
                            comTextView = (TextView) getView().findViewById(R.id.commNameFill);
                            //Flower info
                            flowerColorTV = (TextView) getView().findViewById(R.id.flowerColorFill);
                            flowerConsTV = (TextView) getView().findViewById(R.id.flowerConsFill);
                             //Foliage info
                            foliageColorTV = (TextView) getView().findViewById(R.id.foliageColorFill);
                            foliagePorositySummerTV = (TextView) getView().findViewById(R.id.foliagePorositySummerFill);
                            foliagePorosityWinterTV = (TextView) getView().findViewById(R.id.foliagePorosityWinterFill);
                            foliageTextureTV = (TextView) getView().findViewById(R.id.foliageTextureFill);
                            //FruitSeed info
                            fruitSeedColorTV = (TextView) getView().findViewById(R.id.fruitSeedColorFill);
                            fruitSeedConsTV = (TextView) getView().findViewById(R.id.fruitSeedConsFill);
                            fruitSeedSeedAbundanceTV = (TextView) getView().findViewById(R.id.fruitSeedSeedAbundanceFill);
                            fruitSeedSeedPeriodBeginTV= (TextView) getView().findViewById(R.id.fruitSeedSeedPeriodBeginFill);
                            fruitSeedSeedPeriodEndTV = (TextView) getView().findViewById(R.id.fruitSeedSeedPeriodEndFill);
                            fruitSeedSeedPersistenceTV = (TextView) getView().findViewById(R.id.fruitSeedSeedPersistenceFill);
                            //Growth info
                           growthPhMaxTV = (TextView) getView().findViewById(R.id.growthPhMaxFill);
                           growthPhMinTV = (TextView) getView().findViewById(R.id.growthPhMinFill);
                           precipMaxInchesTV = (TextView) getView().findViewById(R.id.precipMaxInchesFill);
                           precipMinInchesTV = (TextView) getView().findViewById(R.id.precipMinInchesFill);
                           tempMinCTV = (TextView) getView().findViewById(R.id.tempMinCFill);
                           tempMinFTV = (TextView) getView().findViewById(R.id.tempMinFFill);
                            //Specification info
                           speciGrowthFormTV = (TextView) getView().findViewById(R.id.specificationGrowthFormFill);
                           speciGrowthHabitTV = (TextView) getView().findViewById(R.id.specificationGrowthHabitFill);
                           speciGrowthRateTV = (TextView) getView().findViewById(R.id.specificationGrowthRateFill);
                           speciGrowthShapeTV = (TextView) getView().findViewById(R.id.specificationShapeOriFill);
                            //Seed info
                            seedBloomPeriodTV = (TextView) getView().findViewById(R.id.seedBloomPeriodFill);
                            seedCommAvTV = (TextView) getView().findViewById(R.id.seedCommAvFill);
                            seedSeedSpreadRateTV = (TextView) getView().findViewById(R.id.seedSeedSpreadRateFill);
                            seedSeedPerPoundTV = (TextView) getView().findViewById(R.id.seedSeedPerPoundFill);
                            //Pictures
                            imgV = (ImageView) getView().findViewById(R.id.plantImage);
                            //Add to garden
                            addToGarden = (Button) getView().findViewById(R.id.addToGarden);

                            //Assign Scientific Name
                            String sciName = parseJsonSciName(plant);
                            if(sciName != ""){
                                sciTextView.setText(sciName);
                            }else{
                                sciTextView.setText("No Scientific Name Given");
                            }

                            //Assign Common Name
                            String comName = parseJsonComName(plant);
                            if(comName != ""){
                                //insert into fill
                                comTextView.setText(comName);
                            }else{
                                comTextView.setText("No Common Name Given");
                            }

                            //Assign function addToGarden to the Add To Garden button
                            addToGarden.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    System.out.println("You added to garden");
                                    Toast.makeText(getContext(), "Added to your Garden!", Toast.LENGTH_SHORT).show();
                                }
                            });

                            //Assign pictures
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

                            //Assign Flower Color
                            flowerColorTV.setText(plant.getMain_species().getFlower().getColor());
                            //Assign Flower Con
                            flowerConsTV.setText(plant.getMain_species().getFlower().getConspicuous());

                            //Assign Foliage Color
                            foliageColorTV.setText(plant.getMain_species().getFoliage().getColor());
                            //Assign Foliage Porosity Summer
                            foliagePorositySummerTV.setText(plant.getMain_species().getFoliage().getPorosity_summer());
                            //Assign Foliage Porosity Winter
                            foliagePorosityWinterTV.setText(plant.getMain_species().getFoliage().getPorosity_winter());
                            //Assign Foliage Texture
                            foliageTextureTV.setText(plant.getMain_species().getFoliage().getTexture());

                            //Assign FruitSeed Color
                            fruitSeedColorTV.setText(plant.getMain_species().getFruit_or_seed().getColor());
                            //Assign FruitSeed Cons
                            fruitSeedConsTV.setText(plant.getMain_species().getFruit_or_seed().getConspicuous());
                            //Assign FruitSeed Seed Abundance
                            fruitSeedSeedAbundanceTV.setText(plant.getMain_species().getFruit_or_seed().getSeed_abundance());
                            //Assign FruitSeed Seed Period Begin
                            fruitSeedSeedPeriodBeginTV.setText(plant.getMain_species().getFruit_or_seed().getSeed_period_begin());
                            //Assign FruitSeed Seed Period End
                            fruitSeedSeedPeriodEndTV.setText(plant.getMain_species().getFruit_or_seed().getSeed_period_end());
                            //Assign FruitSeed Seed Persistence
                            fruitSeedSeedPersistenceTV.setText(plant.getMain_species().getFruit_or_seed().getSeed_persistence());


                            //Assign Growth PH Max
                            growthPhMaxTV.setText(plant.getMain_species().getGrowth().getPh_maximum() + "");
                            //Assign Growth PH Min
                            growthPhMinTV.setText(plant.getMain_species().getGrowth().getPh_minimum() + "");
                            //Assign Precipitation Max Inches
                            precipMaxInchesTV.setText(plant.getMain_species().getGrowth().getPrecipitation_maximum().getInches() + "");
                            //Assign Precipitation Min Inches
                            precipMinInchesTV.setText(plant.getMain_species().getGrowth().getPrecipitation_minimum().getInches() + "");
                            //Assign Temp Min Celsius
                            tempMinCTV.setText(plant.getMain_species().getGrowth().getTemperature_minimum().getDeg_c() + "");
                            //Assign Temp Min Fahr
                            tempMinFTV.setText(plant.getMain_species().getGrowth().getTemperature_minimum().getDeg_f() + "");

                            //Assign Specification Growth Form
                            String b = plant.getMain_species().getSpecifications().getGrowth_form();
                            speciGrowthFormTV.setText(plant.getMain_species().getSpecifications().getGrowth_form());
                            //Assign Specification Growth Habit
                            String b1 = plant.getMain_species().getSpecifications().getGrowth_habit();
                            speciGrowthHabitTV.setText(plant.getMain_species().getSpecifications().getGrowth_habit());
                            //Assign Specification Growth Rate
                            String b2 = plant.getMain_species().getSpecifications().getGrowth_rate();
                            speciGrowthRateTV.setText(plant.getMain_species().getSpecifications().getGrowth_rate());
                            //Assign Specification Shape Ori
                            String b3 = plant.getMain_species().getSpecifications().getShape_and_orientation();
                            speciGrowthShapeTV.setText(plant.getMain_species().getSpecifications().getShape_and_orientation());

                            //Assign Seed Bloom Period
                            String a = plant.getMain_species().getSeed().getBloom_period();
                            seedBloomPeriodTV.setText(plant.getMain_species().getSeed().getBloom_period());
                            //Assign Seed Commercially Available
                            String a1 = plant.getMain_species().getSeed().getCommercial_availability();
                            seedCommAvTV.setText(plant.getMain_species().getSeed().getCommercial_availability());
                            //Assign Seed Spread Rate
                            String a2 = plant.getMain_species().getSeed().getSeed_spread_rate();
                            seedSeedSpreadRateTV.setText(plant.getMain_species().getSeed().getSeed_spread_rate());
                            //Assign Seeds Per Pound
                            String a3 =plant.getMain_species().getSeed().getSeeds_per_pound() + "";
                            seedSeedPerPoundTV.setText(plant.getMain_species().getSeed().getSeeds_per_pound() + "");
                            System.out.println(a);
                            System.out.println(a1);
                            System.out.println(a2);
                            System.out.println(a3);
                            System.out.println(b);
                            System.out.println(b1);
                            System.out.println(b2);
                            System.out.println(b3);

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
        get_url = "https://trefle.io/api/plants/"+ plantId +"?token="+token;
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
        String[] response = new String[plant.getImages().length];

        //Confirm that we have urls
        if(plant.getImages().length != 0){
            for(int i = 0; i < plant.getImages().length; i++) {
                response[i] = plant.getImages()[i].getUrl();
            }
        }
        return response;
    }


}
