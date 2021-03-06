package f15.delta.com.fdoodle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsActivity extends ActionBarActivity {

    private List<Cluster> clusters;
    public List<Event> events;
    public List<Event> cluster0;
    public List<Event> cluster1;
    public List<Event> cluster2;
    public List<Event> cluster3;
    public List<Event> cluster4;
    public List<Event> cluster5;
    public List<Event> cluster6;
    public List<Event> cluster7;
    public List<Event> cluster8;
    public List<Event> cluster9;
    public List<Event> cluster10;
    public ArrayList<Integer> colours;
    RecyclerView rv;
    int touch_check=1;
    private  Read_write_file fileOps;
    EventAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        fileOps = new Read_write_file(this);
        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        rv = (RecyclerView)findViewById(R.id.rv);
        initializeCluster();
        initializeEvents();
        separateEvents();
        final GridLayoutManager llm = new GridLayoutManager(EventsActivity.this,2);
        colours=new ArrayList<Integer>();
        colours.add(getResources().getColor(R.color.ColorUpcoming));
        colours.add(getResources().getColor(R.color.ColorRaffle));
        colours.add(getResources().getColor(R.color.ColorDoodle));
        colours.add(getResources().getColor(R.color.ColorProfile));
        colours.add(getResources().getColor(R.color.ColorSchedule));
        colours.add(getResources().getColor(R.color.ColorEvents));
        RecycleAdapter_cluster adapter = new RecycleAdapter_cluster(clusters,EventsActivity.this,colours);
        rv.setAdapter(adapter);
        rv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {


            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    //Drawer.closeDrawers();
                    int pos = recyclerView.getChildPosition(child);
                    View single = recyclerView.getChildAt(recyclerView.getChildPosition(child));
                    //   TextView t = (TextView)single.findViewById(R.id.cluster_name);
                    ///String s = clusters.get(pos).getName();
                    //Toast.makeText(EventsActivity.this, s, Toast.LENGTH_SHORT).show();
                    if(touch_check==1) {
                        touch_check = 2;
                        switch (pos) {
                            case 0:
                                EventAdapter adapter = new EventAdapter(cluster0,EventsActivity.this,colours);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                break;
                            case 1:
                                adapter = new EventAdapter(cluster1,EventsActivity.this,colours);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                break;
                            case 2:
                                adapter = new EventAdapter(cluster2,EventsActivity.this,colours);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                break;
                            case 3:
                                adapter = new EventAdapter(cluster3,EventsActivity.this,colours);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                break;
                            case 4:
                                adapter = new EventAdapter(cluster4,EventsActivity.this,colours);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                break;
                            case 5:
                                adapter = new EventAdapter(cluster5,EventsActivity.this,colours);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                break;
                            case 6:
                                adapter = new EventAdapter(cluster6,EventsActivity.this,colours);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                break;
                            case 7:
                                adapter = new EventAdapter(cluster7,EventsActivity.this,colours);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                break;
                            case 8:
                                adapter = new EventAdapter(cluster8,EventsActivity.this,colours);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                break;
                            case 9:
                                adapter = new EventAdapter(cluster9,EventsActivity.this,colours);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                break;
                            case 10:
                                adapter = new EventAdapter(cluster10,EventsActivity.this,colours);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                break;


                        }
                    }
                    else {
                        Intent i = new Intent(EventsActivity.this,UserProfile.class);
                        startActivity(i);
                    }


                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });
        rv.setLayoutManager(llm);






    }
    public void separateEvents(){

        cluster0 = new ArrayList<>();
        cluster1 = new ArrayList<>();
        cluster2 = new ArrayList<>();
        cluster3 = new ArrayList<>();
        cluster4 = new ArrayList<>();
        cluster5 = new ArrayList<>();
        cluster6 = new ArrayList<>();
        cluster7 = new ArrayList<>();
        cluster8 = new ArrayList<>();
        cluster9 = new ArrayList<>();
        cluster10 = new ArrayList<>();
        for(int i=0;i<events.size();i++){

            Event temp = new Event(events.get(i).id,
                    events.get(i).name,
                    events.get(i).start_time,
                    events.get(i).end_time,
                    events.get(i).venue,
                    events.get(i).desc,
                    events.get(i).date,
                    events.get(i).cluster
            );
            if(temp.cluster.equals("dance"))cluster0.add(events.get(i));
            else if(temp.cluster.equals("music"))cluster1.add(temp);
            else if(temp.cluster.equals("shruthilaya"))cluster2.add(events.get(i));
            else if(temp.cluster.equals("dramatics"))cluster3.add(events.get(i));
            else if(temp.cluster.equals("cinematics"))cluster4.add(events.get(i));
            else if(temp.cluster.equals("fashionitas"))cluster5.add(events.get(i));
            else if(temp.cluster.equals("gaming"))cluster6.add(events.get(i));
            else if(temp.cluster.equals("arts"))cluster7.add(events.get(i));
            else if(temp.cluster.equals("hindilits"))cluster8.add(events.get(i));
            else if(temp.cluster.equals("englishlits"))cluster9.add(events.get(i));
            else if(temp.cluster.equals("tanillits"))cluster10.add(events.get(i));

        }
    }






    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }
    public void callEvents(RecyclerView v){
        View r = v.getChildAt(0);


    }

    public  class Cluster {

        String name;

        Cluster(String name) {
            this.name = name;


        }

        public String getName() {
            return name;
        }
    }
    public void initializeCluster(){
        clusters = new ArrayList<>();
        clusters.add(new Cluster("Dance"));
        clusters.add(new Cluster("Music"));
        clusters.add(new Cluster("Shruthilaya"));
        clusters.add(new Cluster("Dramatics"));
        clusters.add(new Cluster("Cinematics"));
        clusters.add(new Cluster("Fashionitas"));
        clusters.add(new Cluster("Gaming"));
        clusters.add(new Cluster("Arts"));
        clusters.add(new Cluster("Hindi Lits"));
        clusters.add(new Cluster("English Lits"));
        clusters.add(new Cluster("Tamil Lits"));

    }
    public void initializeEvents(){

        events = new ArrayList<>();
        String[] description = new String[100];
        String response = fileOps.readFromFile("description.txt");
        try {
            JSONObject jsonObject = new JSONObject(response);
            String data = jsonObject.getString("data");
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                description[i]=jsonObject.getString("event_desc");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        response = fileOps.readFromFile("upcoming.txt");
        try {
            JSONObject jsonObject = new JSONObject(response);
            String data = jsonObject.getString("data");
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                events.add(new Event(
                        jsonObject.getInt("event_id"),
                        jsonObject.getString("event_name"),
                        jsonObject.getString("event_start_time"),
                        jsonObject.getString("event_end_time"),
                        jsonObject.getString("event_venue"),
                        description[i],
                        jsonObject.getString("event_date"),
                        jsonObject.getString("event_cluster")
                ));


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public class Event {

        public Integer id ;
        public String name;
        public String start_time;
        public String end_time;
        public String venue;
        public String desc;
        public String date;
        public String cluster;
        private Read_write_file fileOps;



        public Event(Integer id,String name,String start_time,String end_time,String venue,String desc,String date,String cluster)
        {

            this.id=id;
            this.name=name;
            this.start_time=start_time;
            this.end_time=end_time;
            this.venue=venue;
            this.cluster=cluster;
            this.date=date;
            this.desc=desc;




        }




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(touch_check==2)touch_check=1;
    }
}

