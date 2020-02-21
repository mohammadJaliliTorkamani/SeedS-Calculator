package com.seeds.seeds_calculator;

import java.util.ArrayList;

public class MathOperations {
    public String handleInputString(String input) {
        ArrayList<Integer> indexes;
        for (int i = 0; i < input.length(); i++) {
            if (i == input.length() - 1 && input.toCharArray()[i] == '$') {
                input = input.substring(0, i);
            } else if (input.toCharArray()[i] == '$' || input.toCharArray()[i] == '\\' || input.toCharArray()[i] == '{' ||
                    input.toCharArray()[i] == '}' || input.toCharArray()[i] == '|') {
                input = input.substring(0, i) + input.substring(i + 1);
                i--;
            }
        }
        for (int i = 0; i < input.length(); i++) {

            if ((indexes = exist("times", input, i)) != null) {
                int deleteNumber = 0;
                for (Integer index : indexes) {
                    input = input.substring(0, index - deleteNumber) + input.substring(index + 1 - deleteNumber);
                    deleteNumber++;
                }
                input = input.substring(0, indexes.get(0)) + "*" + input.substring(indexes.get(0));
            } else if ((indexes = exist("fracddx()mid _x=()", input, i)) != null) {
                String lim = "";
                String function = "";
                int check = 0;
                for (int j = 0; j < indexes.size(); j++) {
                    int k = 0;
                    if (input.toCharArray()[indexes.get(j)] == '(') {
                        for (k = j; k < indexes.size(); k++) {
                            if (input.toCharArray()[indexes.get(k)] == ')') {
                                break;
                            }
                        }
                        if (check == 0) {
                            function = input.substring(indexes.get(j) + 1, indexes.get(k));
                            check++;
                        } else if (check == 1) {
                            lim = input.substring(indexes.get(j) + 1, indexes.get(k));
                            check++;
                        }
                        j = k;
                    }
                }
                input = input.substring(0, indexes.get(0)) + "der(" + function + ",x," + lim + ")" +
                        input.substring(indexes.get(indexes.size() - 1) + 1);

            } else if ((indexes = exist("div", input, i)) != null) {
                int deleteNumber = 0;
                for (Integer index : indexes) {
                    input = input.substring(0, index - deleteNumber) + input.substring(index + 1 - deleteNumber);
                    deleteNumber++;
                }
                input = input.substring(0, indexes.get(0)) + "/" + input.substring(indexes.get(0));
            } else if ((indexes = exist("()frac()()", input, i)) != null) {
                int deleteNumber = 0;
                for (Integer index : indexes) {
                    if (input.toCharArray()[index - deleteNumber] == 'f' || input.toCharArray()[index - deleteNumber] == 'a' ||
                            input.toCharArray()[index - deleteNumber] == 'r' || input.toCharArray()[index - deleteNumber] == 'c') {
                        input = input.substring(0, index - deleteNumber) + input.substring(index + 1 - deleteNumber);
                        deleteNumber++;
                    }

                }
                input = input.substring(0, indexes.get(2)) + "+" + input.substring(indexes.get(2));
                input = input.substring(0, indexes.get(8) - 4) + "/" + input.substring(indexes.get(8) - 2);
            } else if ((indexes = exist("frac()()", input, i)) != null) {
                int deleteNumber = 0;
                for (Integer index : indexes) {
                    if (input.toCharArray()[index - deleteNumber] == 'f' || input.toCharArray()[index - deleteNumber] == 'a' ||
                            input.toCharArray()[index - deleteNumber] == 'r' || input.toCharArray()[index - deleteNumber] == 'c') {
                        input = input.substring(0, index - deleteNumber) + input.substring(index + 1 - deleteNumber);
                        deleteNumber++;
                    }

                }
                input = input.substring(0, indexes.get(6) - 5) + "/" + input.substring(indexes.get(6) - 3);
            } else if ((indexes = exist("sqrt[(2)]()", input, i)) != null) {
                for (Integer index : indexes) {
                    if (input.toCharArray()[index] == '[') {
                        input = input.substring(0, index) + input.substring(index + 5);
                        break;

                    }

                }
            } else if ((indexes = exist("int_()^()()", input, i)) != null) {
                String begin = "";
                String end = "";
                String function = "";
                int check = 0;
                for (int j = 0; j < indexes.size(); j++) {
                    int k = 0;
                    if (input.toCharArray()[indexes.get(j)] == '(') {
                        for (k = j; k < indexes.size(); k++) {
                            if (input.toCharArray()[indexes.get(k)] == ')') {
                                break;
                            }
                        }
                        if (check == 0) {
                            begin = input.substring(indexes.get(j) + 1, indexes.get(k));
                            check++;
                        } else if (check == 1) {
                            end = input.substring(indexes.get(j) + 1, indexes.get(k));
                            check++;
                        } else if (check == 2) {
                            function = input.substring(indexes.get(j) + 1, indexes.get(k));
                            check++;
                        }
                        j = k;
                    }
                }
                input = input.substring(0, indexes.get(0)) + input.substring(indexes.get(indexes.size() - 1) + 1);
                input = input.substring(0, indexes.get(0)) + "int(" + function + ",x," + begin + "," + end + ")" + input.substring(indexes.get(0));
            } else if ((indexes = exist("log_()()", input, i)) != null) {
                int deleteNumber = 0;
                for (Integer index : indexes) {
                    if (input.toCharArray()[index - deleteNumber] == '_') {
                        input = input.substring(0, index - deleteNumber) + input.substring(index + 1 - deleteNumber);
                        deleteNumber++;
                    }
                    if (input.toCharArray()[index - deleteNumber] == ')' && input.toCharArray()[index + 1 - deleteNumber] == '(') {
                        input = input.substring(0, index - deleteNumber) + "," + input.substring(index + 2 - deleteNumber);
                        break;
                    }
                }
            } else if ((indexes = exist("log()", input, i)) != null) {
                for (Integer index : indexes) {

                    if (input.toCharArray()[index] == '(') {
                        input = input.substring(0, index + 1) + "10," + input.substring(index + 1);
                        break;
                    }
                }
            } else if ((indexes = exist("sqrt[()]()", input, i)) != null) {
                String n = "";
                String x = "";
                int check = 0;
                for (int j = 0; j < indexes.size(); j++) {
                    int k = 0;
                    if (input.toCharArray()[indexes.get(j)] == '(') {
                        for (k = j; k < indexes.size(); k++) {
                            if (input.toCharArray()[indexes.get(k)] == ')') {
                                break;
                            }
                        }
                        if (check == 0) {
                            n = input.substring(indexes.get(j) + 1, indexes.get(k));
                            check++;
                        } else if (check == 1) {
                            x = input.substring(indexes.get(j) + 1, indexes.get(k));
                            check++;
                        }
                        j = k;
                    }
                }
                input = input.substring(0, indexes.get(0)) + "root(" + n + "," + x + ")" + input.substring(indexes.get(indexes.size() - 1) + 1);

            } else if ((indexes = exist("sum_()^()()", input, i)) != null) {
                String begin = "";
                String end = "";
                String function = "";
                int check = 0;
                for (int j = 0; j < indexes.size(); j++) {
                    int k = 0;
                    if (input.toCharArray()[indexes.get(j)] == '(') {
                        for (k = j; k < indexes.size(); k++) {
                            if (input.toCharArray()[indexes.get(k)] == ')') {
                                break;
                            }
                        }
                        if (check == 0) {
                            begin = input.substring(indexes.get(j) + 1, indexes.get(k));
                            check++;
                        } else if (check == 1) {
                            end = input.substring(indexes.get(j) + 1, indexes.get(k));
                            check++;
                        } else if (check == 2) {
                            function = input.substring(indexes.get(j) + 1, indexes.get(k));
                            check++;
                        }
                        j = k;
                    }
                }
                input = input.substring(0, indexes.get(0)) + "sum(x," + begin + "," + end + "," + function + ")" +
                        input.substring(indexes.get(indexes.size() - 1) + 1);

            }


        }
        input = correctAngle(input, 0);
        return input;
    }

    public String correctAngle(String input, int kind) {
        if (kind == 2) {
            return input;
        }
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if ((indexes = exist("sin()", input, i)) != null || (indexes = exist("cos()", input, i)) != null
                    || (indexes = exist("tan()", input, i)) != null || (indexes = exist("sinh()", input, i)) != null
                    || (indexes = exist("cosh()", input, i)) != null || (indexes = exist("tanh()", input, i)) != null) {
                for (int j = 0; j < indexes.size(); j++) {
                    int k = 0;
                    if (input.toCharArray()[indexes.get(j)] == '(') {
                        for (k = j; k < indexes.size(); k++) {
                            if (input.toCharArray()[indexes.get(k)] == ')') {
                                i = k;
                                break;
                            }
                        }
                        if (kind == 0) {
                            input = input.substring(0, indexes.get(j) + 1) + "(" + input.substring(indexes.get(j) + 1, indexes.get(k)) + ")*pi/180" + input.substring(indexes.get(k));
                        } else if (kind == 1) {
                            input = input.substring(0, indexes.get(j) + 1) + "(" + input.substring(indexes.get(j) + 1, indexes.get(k)) + ")*pi/200" + input.substring(indexes.get(k));

                        }
                    }
                }

            }
            if ((indexes = exist("arcsin()", input, i)) != null || (indexes = exist("arccos()", input, i)) != null
                    || (indexes = exist("arctan()", input, i)) != null || (indexes = exist("arcsinh()", input, i)) != null
                    || (indexes = exist("arccosh()", input, i)) != null || (indexes = exist("arctanh()", input, i)) != null) {
                for (int j = 0; j < indexes.size(); j++) {
                    int k = 0;
                    if (input.toCharArray()[indexes.get(j)] == '(') {
                        for (k = j; k < indexes.size(); k++) {
                            if (input.toCharArray()[indexes.get(k)] == ')') {
                                i = k;
                                break;
                            }
                        }
                        if (kind == 0) {
                            input = input.substring(0, indexes.get(k) + 1) + "/pi*180" + input.substring(indexes.get(k) + 1);
                        } else if (kind == 1) {
                            input = input.substring(0, indexes.get(k) + 1) + "/pi*200" + input.substring(indexes.get(k) + 1);

                        }
                    }
                }

            }
        }
        return input;
    }

    private ArrayList<Integer> exist(String s, String input, int i) {
        ArrayList<Integer> indexes = new ArrayList<>();
        if (input.toCharArray()[i] != s.toCharArray()[0])
            return null;
        indexes.add(i);
        i++;
        int pCheck = 0;
        for (int count = 1; count < s.length() && i < input.length(); i++) {
            if (input.toCharArray()[i] == s.toCharArray()[count] && pCheck == 0) {
                count++;
                indexes.add(i);
            } else if (input.toCharArray()[i] == '{' || input.toCharArray()[i] == '(') {
                pCheck++;
            } else if (input.toCharArray()[i] == '}' || input.toCharArray()[i] == ')') {
                if (pCheck > 0) {
                    pCheck--;
                }
            }
        }
        if (indexes.size() == s.length())
            return indexes;
        return null;
    }
}
