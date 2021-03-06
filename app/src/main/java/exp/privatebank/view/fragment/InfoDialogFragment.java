package exp.privatebank.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import exp.privatebank.R;
import exp.privatebank.pojo.DevicesPOJO.Device;
import exp.privatebank.view.WorkTimeAdapter;
import exp.privatebank.view.activity.DetailMapsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoDialogFragment extends DialogFragment {

    public static final Integer DEVICE_CONFIRM = 0;

    public static final String DEVICE = "device";
    public static final String INFO_TAG = "infoTag";
    private Device mDevice;
    private ArrayList<String> mWorkTimeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info_dialog, container, false);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        Bundle bundleArguments = getArguments();
        mDevice = bundleArguments.getParcelable(DEVICE);

        TextView typeAnswer = (TextView) v.findViewById(R.id.typeAnswer);
        typeAnswer.setText(mDevice.getType());
        TextView cityAnswer = (TextView) v.findViewById(R.id.cityAnswer);
        cityAnswer.setText(mDevice.getCityRU());
        TextView placeAnswer = (TextView) v.findViewById(R.id.placeAnswer);
        placeAnswer.setText(mDevice.getPlaceRu());
        TextView addressAnswer = (TextView) v.findViewById(R.id.addressAnswer);
        addressAnswer.setText(mDevice.getFullAddressRu());

        mWorkTimeList = new ArrayList<>();
        setWorkTime(mWorkTimeList);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new WorkTimeAdapter(mWorkTimeList));

        Button cancelBtn = (Button) v.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        Button confirmBtn = (Button) v.findViewById(R.id.confirmBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    DetailMapsActivity a = (DetailMapsActivity) getActivity();
                    a.onActivityResult(DEVICE_CONFIRM, 1, new Intent());

                dismiss();
            }
        });

        return v;
    }

    private void setWorkTime(ArrayList<String> workTimeList){
        workTimeList.add(mDevice.getTw().getMon());
        workTimeList.add(mDevice.getTw().getTue());
        workTimeList.add(mDevice.getTw().getWed());
        workTimeList.add(mDevice.getTw().getThu());
        workTimeList.add(mDevice.getTw().getFri());
        workTimeList.add(mDevice.getTw().getSat());
        workTimeList.add(mDevice.getTw().getSun());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
