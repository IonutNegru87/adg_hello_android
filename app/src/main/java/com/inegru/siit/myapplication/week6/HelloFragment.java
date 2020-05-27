package com.inegru.siit.myapplication.week6;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.inegru.siit.myapplication.R;

import static com.inegru.siit.myapplication.week6.HelloFragmentDynamicActivity.EXTRA_KEY_INT;
import static com.inegru.siit.myapplication.week6.HelloFragmentDynamicActivity.EXTRA_KEY_STRING;

public class HelloFragment extends Fragment {

    private TheListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hello, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView count = view.findViewById(R.id.tv_count);
        TextView label = view.findViewById(R.id.tv_label);

        Bundle bundle = getArguments();
        if (null != bundle) {
            count.setText(String.valueOf(bundle.getInt(EXTRA_KEY_INT)));
            label.setText(bundle.getString(EXTRA_KEY_STRING));
        }

        view.findViewById(R.id.btn_doWork).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.doSomeWork("Sent from fragment!");
                } else {
                    Toast.makeText(v.getContext(), "Action not supported!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void showCurrentFragmentName() {
        Toast.makeText(getContext(), "Hello, we are in\n" + this.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() instanceof TheListener) {
            listener = (TheListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }
}
