package edu.uncc.weather;
/*
InClass08
CitiesFragment
Andrew Brown
Mehk Heath
*/

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import edu.uncc.weather.databinding.FragmentCitiesBinding;

public class CitiesFragment extends Fragment {
    FragmentCitiesBinding binding;
    final String TAG = "demo";

    public CitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCitiesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    ArrayAdapter<DataService.City> adapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Cities");
        adapter = new ArrayAdapter<DataService.City>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, DataService.cities);
        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mListener.gotoCurrentWeather(adapter.getItem(position));
               // Log.d(TAG, "onItemClick: " + adapter.getItem(position));
            }
        });
    }

    CitiesFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CitiesFragmentListener) context;
    }

    interface CitiesFragmentListener {
        void gotoCurrentWeather(DataService.City city);
    }
}