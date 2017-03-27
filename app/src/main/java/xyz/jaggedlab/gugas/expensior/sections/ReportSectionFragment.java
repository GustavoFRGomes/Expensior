package xyz.jaggedlab.gugas.expensior.sections;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;

import xyz.jaggedlab.gugas.expensior.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReportSectionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReportSectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportSectionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ReportSectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportSectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportSectionFragment newInstance(String param1, String param2) {
        ReportSectionFragment fragment = new ReportSectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_section, container, false);
        // Inflate the layout for this fragment

        int blueAccent = ContextCompat.getColor(this.getContext(), R.color.colorAccent);
        int orangeColor = ContextCompat.getColor(this.getContext(), R.color.dialog_title);
        int redColor = ContextCompat.getColor(this.getContext(), android.R.color.holo_red_dark);

        DecoView roundChart = ((DecoView) rootView.findViewById(R.id.round_chart));
        roundChart.addSeries(new SeriesItem.Builder(blueAccent)
                .setRange(0, 100, 100)
                .setLineWidth(32f)
                .build());

        roundChart.addSeries(new SeriesItem.Builder(orangeColor)
                .setRange(0, 100, 34)
                .setLineWidth(23f)
                .build());

        roundChart.addSeries(new SeriesItem.Builder(redColor)
                .setRange(34, 100, 56)
                .setLineWidth(15f)
                .build());

        return rootView;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
