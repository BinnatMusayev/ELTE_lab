package practice.com.eltelinks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

public class PDF_Fragment extends Fragment {

    private PDFView pdfView;
    private Button closeBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pdf_view, container, false);

        pdfView = view.findViewById(R.id.pdf_container);
        String fileName = getArguments().getString("file");
        pdfView.fromAsset(fileName).load();



        //close button
        closeBtn = view.findViewById(R.id.pdf_close_btn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Timetables_Fragment()).commit();
            }
        });


        return view;
    }
}
