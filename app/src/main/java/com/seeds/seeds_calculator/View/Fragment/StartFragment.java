package com.seeds.seeds_calculator.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.seeds.seeds_calculator.MathOperations;
import com.seeds.seeds_calculator.Pure;
import com.seeds.seeds_calculator.R;
import com.seeds.seeds_calculator.Utils.Constants;
import com.seeds.seeds_calculator.Utils.ContextHelper;
import com.seeds.seeds_calculator.Utils.Helper;

import org.mariuszgromada.math.mxparser.Expression;

import io.github.kexanie.library.MathView;

public class StartFragment extends Fragment implements View.OnClickListener {
    static int previousButton = 0;
    private View view;
    private MathView fMathView;
    private ImageView on;
    private MathView sMathView;
    private boolean isOn;
    private String fLine = "";
    private String sLine = "";
    private Pure bi;
    private int[] buttonIds = {R.id.but6, R.id.but7, R.id.but8, R.id.but9, R.id.but10, R.id.but11, R.id.but12,
            R.id.but13, R.id.but14, R.id.but15, R.id.but16, R.id.but17, R.id.but18, R.id.but19, R.id.but20,
            R.id.but21, R.id.but22, R.id.but23, R.id.but24, R.id.but25, R.id.but26, R.id.but27, R.id.but28,
            R.id.but29, R.id.but30, R.id.but31, R.id.but32, R.id.but33, R.id.but34, R.id.but35, R.id.but36,
            R.id.but37, R.id.but38, R.id.but39, R.id.but40, R.id.but41, R.id.but42, R.id.but43, R.id.but44,
            R.id.but45, R.id.but46, R.id.but47, R.id.c_left, R.id.c_right, R.id.c_top, R.id.replay};
    private int[] imageButtonId = {R.id.but1, R.id.but2, R.id.c_buttom, R.id.but4, R.id.but5};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_start, container, false);
        findViews();
        initialize();
        manageListeners();
        return view;
    }

    private void findViews() {
        on = view.findViewById(R.id.but5);
        fMathView = view.findViewById(R.id.f_line);
        sMathView = view.findViewById(R.id.s_line);
    }

    private void initialize() {
        Helper.recordEventView("StartFragment");
        isOn = false;
        bi = new Pure(view);
        fLine = "$$|$$";
        fMathView.setText(fLine);
    }

    private void manageListeners() {
        for (int buttonId : buttonIds) {
            Button button = view.findViewById(buttonId);
            button.setOnClickListener(this);
        }
        for (int value : imageButtonId) {
            ImageButton imageButton = view.findViewById(value);
            imageButton.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.but5 && !isOn) {
            isOn = true;
            Helper.toast("ماشین حساب روشن شد", Constants.ToastMode.SUCCESS);
            Helper.recordEventClick("StartFragment", "calculator _ON_ button");
        } else if (!isOn)
            Helper.toast("ماشین حساب کاسیو خاموش است", Constants.ToastMode.WARNING);
        else {
            switch (v.getId()) {
                case (R.id.but4):
                    Helper.showSnackbar(view);
                    break;
                case (R.id.but47):
                    MathOperations operate = new MathOperations();
                    sLine = operate.handleInputString(fMathView.getText());
                    Expression expression = new Expression(sLine);
                    sMathView.setText(Double.toString(expression.calculate()));
                    break;
                case R.id.but5:
                case R.id.but32:
                    fLine = "$$|$$";
                    sLine = "";
                    fMathView.setText(fLine);
                    sMathView.setText(sLine);
                    break;
                default:
                    fLine = bi.getOutput(v, previousButton, ContextHelper.retrieveContext(), fMathView.getText());
                    fMathView.setText(fLine);
                    previousButton = v.getId();
            }
        }
    }
}
