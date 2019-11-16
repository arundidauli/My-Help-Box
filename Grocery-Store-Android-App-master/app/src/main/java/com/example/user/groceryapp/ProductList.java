package com.example.user.groceryapp;
import android.app.AlertDialog;
import android.content.*;
import android.util.AttributeSet;
import android.widget.*;


import java.util.*;

public class ProductList extends Spinner implements
        DialogInterface.OnMultiChoiceClickListener {
    String[] products = null;
    boolean[] selections = null;

    ArrayAdapter<String> arrayadapter;

    public ProductList(Context context) {
        super(context);

        arrayadapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item);
        super.setAdapter(arrayadapter);
    }

    public ProductList(Context context, AttributeSet attrs) {
        super(context, attrs);

        arrayadapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item);
        super.setAdapter(arrayadapter);
    }

    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (selections != null && which < selections.length) {
            selections[which] = isChecked;

            arrayadapter.clear();
            arrayadapter.add(buildSelectedItemString());
        } else {
            throw new IllegalArgumentException("Argument exceeded");
        }
    }

    @Override
    public boolean performClick() {
        AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
        ad.setMultiChoiceItems(products, selections, this);
        ad.show();
        return true;
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        throw new RuntimeException("setAdapter unsupported multi selection");
    }

    public void setItems(String product[]) {
        products = product;
        selections = new boolean[products.length];
        arrayadapter.clear();
        arrayadapter.add(products[0]);
        Arrays.fill(selections, false);
    }

    public void setItems(List<String> item_list) {
        products = item_list.toArray(new String[item_list.size()]);
        selections = new boolean[products.length];
        arrayadapter.clear();
        arrayadapter.add(products[0]);
        Arrays.fill(selections, false);
    }

    public void setSelection(String[] selection) {
        for (String cells : selection) {
            for (int j = 0; j < products.length; ++j) {
                if (products[j].equals(cells)) {
                    selections[j] = true;
                }
            }
        }
    }

    public void setSelection(List<String> selection) {
        for (int i = 0; i < selections.length; i++) {
            selections[i] = false;
        }
        for (String sel : selection) {
            for (int j = 0; j < products.length; ++j) {
                if (products[j].equals(sel)) {
                    selections[j] = true;
                }
            }
        }
        arrayadapter.clear();
        arrayadapter.add(buildSelectedItemString());
    }

    public void setSelection(int index) {
        for (int i = 0; i < selections.length; i++) {
            selections[i] = false;
        }
        if (index >= 0 && index < selections.length) {
            selections[index] = true;
        } else {
            throw new IllegalArgumentException("Index " + index + " exceeded");
        }
        arrayadapter.clear();
        arrayadapter.add(buildSelectedItemString());
    }

    public void setSelection(int[] selectedIndicies) {
        for (int i = 0; i < selections.length; i++) {
            selections[i] = false;
        }
        for (int index : selectedIndicies) {
            if (index >= 0 && index < selections.length) {
                selections[index] = true;
            } else {
                throw new IllegalArgumentException("Index " + index+ " exceeded");
            }
        }
        arrayadapter.clear();
        arrayadapter.add(buildSelectedItemString());
    }

    public List<String> getSelectedStrings() {
        List<String> selected = new LinkedList<String>();
        for (int i = 0; i < products.length; ++i) {
            if (selections[i]) {
                selected.add(products[i]);
            }
        }
        return selected;
    }

    public List<Integer> getSelectedIndicies() {
        List<Integer> selected = new LinkedList<Integer>();
        for (int i = 0; i < products.length; ++i) {
            if (selections[i]) {
                selected.add(i);
            }
        }
        return selected;
    }

    private String buildSelectedItemString() {
        StringBuilder buffer = new StringBuilder();
        boolean foundOne = false;

        for (int i = 0; i < products.length; ++i) {
            if (selections[i]) {
                if (foundOne) {
                    buffer.append(", ");
                }
                foundOne = true;

                buffer.append(products[i]);
            }
        }
        return buffer.toString();
    }

    public String getSelectedItemsAsString() {
        StringBuilder buffer = new StringBuilder();
        boolean found = false;

        for (int i = 0; i < products.length; ++i) {
            if (selections[i]) {
                if (found) {
                    buffer.append(", ");
                }
                found = true;
                buffer.append(products[i]);
            }
        }
        return buffer.toString();
    }
}