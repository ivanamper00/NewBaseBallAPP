package com.ivandeveloper.newbaseballapp.controller.activity.mainFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ivandeveloper.newbaseballapp.R;
import com.ivandeveloper.newbaseballapp.adapter.StandingsAdapter;
import com.ivandeveloper.newbaseballapp.adapter.StandingsListAdapter;
import com.ivandeveloper.newbaseballapp.controller.GlobalController;
import com.ivandeveloper.newbaseballapp.model.StandingsResponse;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StandingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StandingsFragment extends Fragment {
//WebView webView;
GlobalController globalController;
RecyclerView recyclerView;
List<List<StandingsResponse>> standingsList;
CardView cardView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StandingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdatesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StandingsFragment newInstance(String param1, String param2) {
        StandingsFragment fragment = new StandingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_standings, container, false);
        globalController = new GlobalController(getContext());
        recyclerView = view.findViewById(R.id.standings_recycler);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        cardView = view.findViewById(R.id.no_data);

        standingsList = globalController.retrieveStandings();
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " + standingsList.get(0).size());
        if(standingsList.size() != 0){
            cardView.setVisibility(View.INVISIBLE);
            StandingsAdapter adapter = new StandingsAdapter(getContext(), standingsList);
            recyclerView.setAdapter(adapter);
        }
        else{
            cardView.setVisibility(View.VISIBLE);
        }


//        webView = view.findViewById(R.id.update_fragment_web_view);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("https://www.google.com/search?rlz=1C1CHBD_enPH919PH919&sxsrf=ALeKk031axulfG8XlKXD6cdCFYB1iXFmug:1602222102766&q=baseball+news&tbm=nws&source=univ&tbo=u&sa=X&ved=2ahUKEwi1l6W65qbsAhV0QRUIHRgUDiYQt8YBKAN6BAgNEBA&biw=1321&bih=2349&dpr=3");
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public void onPageFinished(WebView view, String url)
//            {
//                view.loadUrl("javascript:getValue()");
//                webView.loadUrl("javascript:(function() { " +
//                       getClassName("KojFAc")+
//                        getClassName("BvKjef")+
//                        getIdName("qslc")+
//                        getIdName("sfcnt")+
//                        getIdName("fbar")+
//                        getIdName("center_col")+"})()" );
//            }
//        });
        return view;
    }
//    public String getClassName(String className){
//        return "document.getElementsByClassName('" + className + "')[0].style.display='none';";
//    }
//    public String getIdName(String idName){
//        return "document.getElementById('" + idName + "')[0].style.display='none';";
//    }
}