package labs;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import models.Reminder;

public class ProductLab {
    private static ProductLab sProductLab;

    public static final String REMINDER_ID_TAG = "id_reminders";
    public static final String REMINDER_DESC_TAG = "description";

    public static ProductLab get() {
        if (sProductLab == null)
            sProductLab = new ProductLab();
        return sProductLab;
    }


    public void EditProduct(RequestQueue mQueue, String id_product, int quantity) {
        id_product="hola";
        quantity=1;
        String queryEditProduct = "https://checkitdatabase.000webhostapp.com/edit-product-list.php?id_product=" + id_product + "&quantity=" + quantity;
        JsonRequest mJsonRequest = new JsonObjectRequest(Request.Method.GET, queryEditProduct, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(mJsonRequest);
    }

    public void Productplus1(String id_product){

    }

    public void getQuantity(){

    }
}