package practice.com.eltelinks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Timetables_Fragment extends Fragment {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timetables, container, false);
        //initializing and populating listview
        listView = view.findViewById(R.id.timetable_list_view);
        final String[] timetableNames = {"Master - Data Science", "Master - Software", "Master - Autonomous Systems",
                "Bachelor - First Year", "Bachelor - Upper Year"  };
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                timetableNames);
        listView.setAdapter(arrayAdapter);


        //show pdf
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String)adapterView.getItemAtPosition(i);
                PDF_Fragment pdf_fragment = new PDF_Fragment();
                Bundle bundle = new Bundle();
                switch (item){
                    case "Master - Data Science":
                        bundle.putString("file", "data_science_timetable.pdf");
                        pdf_fragment.setArguments(bundle);
                        break;
                    case "Master - Software":
                        bundle.putString("file", "software_timetable.pdf");
                        pdf_fragment.setArguments(bundle);
                        break;
                    case "Master - Autonomous Systems":
                        bundle.putString("file", "autonomous_systems_timetable.pdf");
                        pdf_fragment.setArguments(bundle);
                        break;
                    case "Bachelor - First Year":
                        bundle.putString("file", "bachelor_fy.pdf");
                        pdf_fragment.setArguments(bundle);
                        break;
                    case "Bachelor - Upper Year":
                        bundle.putString("file", "bachelor_uy.pdf");
                        pdf_fragment.setArguments(bundle);
                        break;
                }


                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        pdf_fragment).commit();
            }
        });

        return view;
    }
}
