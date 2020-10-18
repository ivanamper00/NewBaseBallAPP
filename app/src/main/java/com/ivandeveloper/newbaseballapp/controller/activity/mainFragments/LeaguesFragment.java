package com.ivandeveloper.newbaseballapp.controller.activity.mainFragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivandeveloper.newbaseballapp.R;
import com.ivandeveloper.newbaseballapp.adapter.LeaguesAdapter;
import com.ivandeveloper.newbaseballapp.controller.GlobalController;
import com.ivandeveloper.newbaseballapp.model.LeagueModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeaguesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeaguesFragment extends Fragment {
RecyclerView recyclerView;
GlobalController globalController;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LeaguesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeaguesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeaguesFragment newInstance(String param1, String param2) {
        LeaguesFragment fragment = new LeaguesFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leagues, container, false);
        globalController = new GlobalController(getContext());
        recyclerView = view.findViewById(R.id.leagues_recycler);
        getLeagues();
        return view;
    }

    public void getLeagues(){
        List<LeagueModel.Response> leagues = globalController.retrieveLeagues();
        GridLayoutManager grid = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        LeaguesAdapter adapter = new LeaguesAdapter(getContext(), leagues, globalController);
        recyclerView.setLayoutManager(grid);
        recyclerView.setAdapter(adapter);
    }
}