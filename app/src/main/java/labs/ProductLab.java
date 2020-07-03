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


    public void EditProduct(RequestQueue mQueue, String id_product, int quantity, int id_list) {
        String queryEditProduct = "https://checkitdatabase.000webhostapp.com/edit-product-list.php?id_product=" + id_product + "&quantity=" + quantity + "&id_list=" + id_list;
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

    public void productPlusOne(String id_product, int id_list){
        int newQuantity = getQuantity(id_product, id_list);
        newQuantity++;
        EditProduct(Queue,id_product, newQuantity, id_list);
    }

    public void productMinusOne(String id_product, int id_list){
        int newQuantity = getQuantity(id_product, id_list);
        newQuantity++;
        EditProduct(Queue,id_product, newQuantity, id_list);
    }

    public int getQuantity(String id_product, int id_list){
        /*
        * SELECT quantity FROM shopping_list WHERE ID_product = '{$id_product}' AND ID_list = '{$id_list}'
        * */
    }
}