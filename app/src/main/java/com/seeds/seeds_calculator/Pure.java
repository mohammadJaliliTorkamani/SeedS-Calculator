package com.seeds.seeds_calculator;

import android.content.Context;
import android.view.View;

import com.seeds.seeds_calculator.Utils.Helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pure {
    public static Set<String> leftRightKey = new HashSet<>();
    public static Set<String> deletekey = new HashSet<>();
    boolean shift = false;
    boolean alpha = false;
    private View view;

    public Pure(View view) {
        this.view = view;
    }

    public int findCursorPosition(String input) {
        int position = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.toCharArray()[i] == '|') {
                position = i + 1;
            }
        }
        return position;
    }


    public String getBase() {
        return null;
    }

    public String getOutput(View v, int previousButton, Context context, String input) {
        int cursorPosition;
        switch (v.getId()) {
            case R.id.c_top:
            case R.id.c_left:
                int leftSkipNumber = findLeftSkipNumber(input, leftRightKey);
                input = handleLeftSkip(leftSkipNumber, input);
                break;
            case R.id.c_buttom:
            case R.id.c_right:
                int rightSkipNumber = findRightSkipNumber(input, leftRightKey);
                cursorPosition = findCursorPosition(input);
                input = input.substring(0, cursorPosition - 1) + input.substring(cursorPosition, cursorPosition + rightSkipNumber) + "|" + input.substring(cursorPosition + rightSkipNumber);
                break;
            case R.id.but31:
                input = handleDelete(input, deletekey, leftRightKey, context);
                break;
            default:
                String output = "";
                output = findString(v, previousButton, context);
                cursorPosition = findCursorPosition(input);
                input = input.substring(0, cursorPosition - 1) + output + input.substring(cursorPosition - 1);
        }
        return input;
    }


    private String findString(View v, int previousButton, Context context) {
        switch (v.getId()) {
            case R.id.but43:
                if (shift) {
                    shift = false;
//                    deletekey.add("Rnd{()}");
//                    leftRightKey.add(")}");
//                    leftRightKey.add("Rnd{(");
//                    return "Rnd{()}";
                    Helper.showSnackbar(view.findViewById(R.id.layout));
                } else if (alpha) {
                    return "";
                }
                return "0";
            case R.id.but44:
                if (shift) {
                    shift = false;
//                    deletekey.add("Rand{()}");
//                    leftRightKey.add(")}");
//                    leftRightKey.add("Rand{(");
//                    return "Rand{()}";
                    Helper.showSnackbar(view.findViewById(R.id.layout));
                } else if (alpha) {
                    return "";
                }
                return ".";
            case R.id.but45:
                if (alpha) {
                    alpha = false;
                    return "e";
                }
                if (shift) {
                    shift = false;
                    deletekey.add("\\pi");
                    leftRightKey.add("\\pi");
                    return "\\pi";
                }
                deletekey.add("\\times{10}^{()}");
                leftRightKey.add(")}");
                leftRightKey.add("\\times{10}^{(");
                return "\\times{10}^{()}";
            case R.id.but38:
                if (!shift && !alpha) {
                    return "1";
                }
                return "";
            case R.id.but39:
                if (!shift && !alpha) {
                    return "2";
                }
                return "";
            case R.id.but40:
                if (!shift && !alpha) {
                    return "3";
                }
                return "";
            case R.id.but41:
                if (shift) {
                    shift = false;
//                    deletekey.add("Pol{()}");
//                    leftRightKey.add(")}");
//                    leftRightKey.add("Pol{(");
//                    return "Pol{()}";
                    Helper.showSnackbar(view.findViewById(R.id.layout));
                }
                if (alpha) {
                    return "";
                }
                if (!shift && !alpha) {
                    return "+";
                }
            case R.id.but17:
                if (alpha) {
                    alpha = false;
                    return "B";
                }
                if (!shift && !alpha) {
//                    deletekey.add("{()}^{\\square}");
//                    leftRightKey.add("{(");
//                    leftRightKey.add("{()}^{\\square}");
//                    return "{()}^{\\square}";
                    Helper.showSnackbar(view.findViewById(R.id.layout));
                }
                if (alpha) {
                    return "";
                }
            case R.id.but16:
                if (alpha) {
                    alpha = false;
                    return "A";
                }
                if (!shift && !alpha) {
                    return "-";
                }
                return "";

            case R.id.but42:
                if (shift) {
                    shift = false;
//                    deletekey.add("Rec{()}");
//                    leftRightKey.add(")}");
//                    leftRightKey.add("Rec{(");
//                    return "Rec{()}";
                    Helper.showSnackbar(view.findViewById(R.id.layout));
                }
                if (!shift && !alpha) {
                    return "-";
                }
                return "";
            case R.id.but33:
                if (!shift && !alpha) {
                    return "4";
                }
                return "";
            case R.id.but34:
                if (!shift && !alpha) {
                    return "5";
                }
                return "";
            case R.id.but35:
                if (!shift && !alpha) {
                    return "6";
                }
            case R.id.but36:
                if (shift) {
                    shift = false;
//                    deletekey.add("P{()}");
//                    leftRightKey.add(")}");
//                    leftRightKey.add("P{(");
//                    return "P{()}";
                    Helper.showSnackbar(view.findViewById(R.id.layout));
                }
                if (!shift && !alpha) {
                    deletekey.add("\\times");
                    leftRightKey.add("\\times");
                    return "\\times";
                }
                return "";
            case R.id.but37:
                if (shift) {
                    shift = false;
//                    deletekey.add("C{()}");
//                    leftRightKey.add(")}");
//                    leftRightKey.add("C{(");
//                    return "C{()}";
                    Helper.showSnackbar(view.findViewById(R.id.layout));
                }
                if (!shift && !alpha) {
                    deletekey.add("\\div");
                    leftRightKey.add("\\div");
                    return "\\div";
                }
                return "";
            case R.id.but28:
                if (!shift && !alpha) {
                    return "7";
                }
                return "";
            case R.id.but29:
                if (!shift && !alpha) {
                    return "8";
                }
                return "";
            case R.id.but30:
                if (!shift && !alpha) {
                    return "9";
                }
                return "";
            case R.id.but24:
                if (shift) {
                    shift = false;
                    return "%";
                }
                if (!shift && !alpha) {
                    return "(";
                }
                return "";
            case R.id.but25:
                if (alpha) {
                    alpha = false;
                    return "x";
                }
                if (shift) {
                    shift = false;
                    return ",";
                }
                return ")";
            case R.id.but18:
                if (alpha) {
                    alpha = false;
                    return "C";
                }
                if (shift) {
                    shift = false;
                    deletekey.add("\\mid{()}\\mid");
                    leftRightKey.add("\\mid{(");
                    leftRightKey.add(")}\\mid");
                    return "\\mid{()}\\mid";
                }
                List<String> stringList = new ArrayList<>();
                stringList.add("sinh");
                stringList.add("cosh");
                stringList.add("tanh");
                stringList.add("sinh-1");
                stringList.add("cosh-1");
                stringList.add("tanh-1");
//                showDialogbox(stringList,active,(MathView)this.active.findViewById(R.id.f_line));
                return "";
            case R.id.but19:
                if (alpha) {
                    alpha = false;
                    return "D";
                }
                if (shift) {
                    shift = false;
                    deletekey.add("\\arcsin{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add("\\arcsin{(");
                    return "\\arcsin{()}";
                }
                deletekey.add("\\sin{()}");
                leftRightKey.add(")}");
                leftRightKey.add("\\sin{(");
                return "\\sin{()}";
            case R.id.but20:
                if (shift) {
                    shift = false;
                    deletekey.add("\\arccos{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add("\\arccos{(");
                    return "\\arccos{()}";
                }
                if (!shift && !alpha) {
                    deletekey.add("\\cos{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add("\\cos{(");
                    return "\\cos{()}";
                }
                return "";
            case R.id.but21:
                if (shift) {
                    shift = false;
                    deletekey.add("\\arctan{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add("\\arctan{(");
                    return "\\arctan{()}";
                }
                if (!shift && !alpha) {
                    deletekey.add("\\tan{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add("\\tan{(");
                    return "\\tan{()}";
                }
                return "";
            case R.id.but10:
                if (shift) {
                    shift = false;
                    deletekey.add("{()}\\frac{()}{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add(")}{(");
                    leftRightKey.add(")}\\frac{(");
                    return "{()}\\frac{()}{()}";
                }
                if (!shift && !alpha) {
                    deletekey.add("\\frac{()}{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add(")}{(");
                    leftRightKey.add("\\frac{(");
                    return "\\frac{()}{()}";
                }
                return "";
            case R.id.but11:
                if (shift) {
                    shift = false;
                    deletekey.add("\\sqrt[3]{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add("\\sqrt[3]{(");
                    return "\\sqrt[(3)]{()}";
                }
                if (!shift && !alpha) {
                    deletekey.add("\\sqrt{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add("\\sqrt[(2)]{(");
                    return "\\sqrt[(2)]{()}";
                }
                return "";
            case R.id.but12:
                if (shift) {
                    shift = false;
                    deletekey.add("{()}^3");
                    leftRightKey.add(")}^3");
                    leftRightKey.add("{(");
                    return "{()}^3";
                }
                if (!shift && !alpha) {
                    deletekey.add("{()}^2");
                    leftRightKey.add("{(");
                    leftRightKey.add(")}^2");
                    return "{()}^2";
                }
                return "";
            case R.id.but13:
                if (shift) {
                    shift = false;
                    deletekey.add("\\sqrt[()]{()}");
                    leftRightKey.add("\\sqrt[(");
                    leftRightKey.add(")]{(");
                    leftRightKey.add(")}");
                    return "\\sqrt[()]{()}";
                }
                if (!shift && !alpha) {
                    deletekey.add("{()}^{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add("{(");
                    leftRightKey.add(")}^{(");
                    return "{()}^{()}";
                }
                return "";
            case R.id.but14:
                if (shift) {
                    shift = false;
                    deletekey.add("{10}^{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add("{10}^{(");
                    return "{10}^{()}";
                }
                if (!shift && !alpha) {
                    deletekey.add("\\log{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add("\\log{(");
                    return "\\log{()}";
                }
                return "";
            case R.id.but15:
                if (shift) {
                    shift = false;
                    deletekey.add("{e}^{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add("{e}^{(");
                    return "{e}^{()}";
                }
                if (!shift && !alpha) {
                    deletekey.add("\\ln{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add("\\ln{(");
                    return "\\ln{()}";
                }
                return "";
            case R.id.but7:
                if (shift) {
                    shift = false;
                    deletekey.add("\\frac{d}{dx}{()}\\mid _{x=()}");
                    leftRightKey.add(")}");
                    leftRightKey.add(")}\\mid _{x=(");
                    leftRightKey.add("\\frac{d}{dx}{(");
                    return "\\frac{d}{dx}{()}\\mid _{x=()}";
                }
                if (alpha) {
                    alpha = false;
                    return ":";
                }
                deletekey.add("\\int_{()}^{()}{()}");
                leftRightKey.add(")}");
                leftRightKey.add(")}{(");
                leftRightKey.add(")}^{(");
                leftRightKey.add("\\int_{(");
                return "\\int_{()}^{()}{()}";
            case R.id.but8:
                if (shift) {
                    shift = false;
                    deletekey.add("{()}!");
                    leftRightKey.add("{(");
                    leftRightKey.add(")}!");
                    return "{()}!";
                }
                if (!shift && !alpha) {
                    deletekey.add("{()}^{-1}");
                    leftRightKey.add("{(");
                    leftRightKey.add(")}^{-1}");
                    return "{()}^{-1}";
                }
                return "";
            case R.id.but9:
                if (shift) {
                    shift = false;
                    deletekey.add("\\sum_{()}^{()}{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add(")}{(");
                    leftRightKey.add(")}^{(");
                    leftRightKey.add("\\sum_{(");
                    return "\\sum_{()}^{()}{()}";
                }
                if (!shift && !alpha) {
                    deletekey.add("\\log_{()}{()}");
                    leftRightKey.add(")}");
                    leftRightKey.add(")}{(");
                    leftRightKey.add("\\log_{(");
                    return "\\log_{()}{()}";
                }
                return "";
            case R.id.but1:
                shift = true;
                return "";
            case R.id.but2:
                alpha = true;
                return "";
            case R.id.but46:

                if (shift) {
                    shift = false;

                }
                Helper.showSnackbar(view.findViewById(R.id.layout));
                return "";
            case R.id.but26:
                if (alpha) {
                    alpha = false;
                    return "Y";
                }
                return "";
            case R.id.but27:
                if (alpha) {
                    alpha = false;
                    return "M";
                }
                return "";
            case R.id.but6:
                if (alpha) {
                    alpha = false;
                    return "=";
                }
                return "";

        }
        Helper.showSnackbar(view.findViewById(R.id.layout));
        return "";
    }

    private int findLeftSkipNumber(String input, Set<String> leftRightKey) {
        int cursorPosition = findCursorPosition(input);
        if (cursorPosition == 3)
            return 0;
        int skipNumber = 1;
        for (String str : leftRightKey) {
            if (cursorPosition - 1 - str.length() >= 0) {
                if (input.substring(cursorPosition - 1 - str.length(), cursorPosition - 1).equals(str)) {
                    if (str.length() > skipNumber) {
                        skipNumber = str.length();
                    }
                }
            }
        }
        return skipNumber;
    }

    private int findRightSkipNumber(String input, Set<String> leftRightKey) {
        int cursorPosition = findCursorPosition(input);
        if (cursorPosition == input.length() - 2)
            return 0;
        int skipNumber = 1;
        for (String str : leftRightKey) {
            if (cursorPosition + str.length() < input.length() - 1) {
                if (input.substring(cursorPosition, cursorPosition + str.length()).equals(str)) {
                    if (str.length() >= skipNumber) {
                        skipNumber = str.length();
                    }
                }
            }
        }
        return skipNumber;
    }

    private String handleDelete(String input, Set<String> deletekey, Set<String> leftRightKey, Context context) {
        int cursorPosition = findCursorPosition(input);
        for (String str : deletekey) {
            ArrayList<Integer> indexes = new ArrayList<>();
            indexes = exist(str, input);
            if (indexes != null) {
                for (Integer i : indexes) {
                    input = input.substring(0, i) + input.substring(i + 1);

                }

                return input;
            }
        }
        int leftSkipNumber = findLeftSkipNumber(input, leftRightKey);
        if (leftSkipNumber > 1) {
            input = handleLeftSkip(leftSkipNumber, input);
            return input;
        }
        if (cursorPosition > 3)
            input = input.substring(0, cursorPosition - 2) + input.substring(cursorPosition - 1);
        return input;
    }

    private ArrayList<Integer> exist(String str, String input) {
        ArrayList<Integer> indexes = new ArrayList<>();
        int cursorPosition = findCursorPosition(input);
        int strLength = str.length() - 1;
        int pCheck = 0;
        if (input.toCharArray()[cursorPosition - 2] == str.toCharArray()[strLength]) {
            indexes.add(cursorPosition - 2);
            indexes.add(cursorPosition - 3);
            strLength -= 2;
            for (int i = cursorPosition - 4; i >= 0 && strLength >= 0; i--) {
                if (input.toCharArray()[i] == str.toCharArray()[strLength]) {
                    if (input.toCharArray()[i] == '{' || input.toCharArray()[i] == '(') {
                        if (pCheck == 0) {
                            indexes.add(i);
                            strLength--;
                        } else
                            pCheck--;
                    } else {
                        indexes.add(i);
                        strLength--;
                    }
                } else if (input.toCharArray()[i] == '}' || input.toCharArray()[i] == ')') {
                    pCheck++;
                } else if (input.toCharArray()[i] == '{' || input.toCharArray()[i] == '(') {
                    pCheck--;
                }


            }
        }
        if (indexes.size() == str.length())
            return indexes;
        return null;
    }

    private String handleLeftSkip(int leftSkipNumber, String input) {
        int cursorPosition = findCursorPosition(input);
        input = input.substring(0, cursorPosition - 1 - leftSkipNumber) + "|" + input.substring(cursorPosition - 1 - leftSkipNumber, cursorPosition - 1) + input.substring(cursorPosition);
        return input;
    }


}
