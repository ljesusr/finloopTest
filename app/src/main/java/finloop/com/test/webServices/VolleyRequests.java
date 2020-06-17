package finloop.com.test.webServices;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static finloop.com.test.utils.ParserData.getModelFromJSON;
import static finloop.com.test.utils.SavedVariables.getJwt;

public class VolleyRequests {
    private ServiceResponse response = new ServiceResponse();

    public void createUser(final Context context, @Nullable ServiceResponseInterface serviceResponseInterface, String url, final String email, final String username, final String password) {
        final ServiceResponseInterface listener =  serviceResponseInterface;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String s) {
                        if(listener !=null) {
                            response =  getModelFromJSON(response, s);
                            listener.OnSuccessResponse(response);
                        }
                        Log.d("Response", s);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        try {
                            if(listener !=null)
                                listener.OnErrorResponse(getModelFromJSON(response, error.toString()));

                            Log.d("Error.Response", error.toString());
                        } catch (Exception e) {
                            listener.OnErrorResponse(null);
                            e.printStackTrace();
                        }
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("username", username);
                params.put("password", password);
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }
        };
        requestQueue.add(postRequest);
    }

    public void login(final Context context, @Nullable ServiceResponseInterface serviceResponseInterface, String url, final String email, final String password) {
        final ServiceResponseInterface listener =  serviceResponseInterface;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String s) {
                        if(listener !=null) {
                            response =  getModelFromJSON(response, s);
                            listener.OnSuccessResponse(response);
                        }
                        Log.d("Response", s);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        try {
                            if(listener !=null)
                                listener.OnErrorResponse(getModelFromJSON(response, error.toString()));
                            Log.d("Error.Response", error.toString());
                        } catch (Exception e) {
                            listener.OnErrorResponse(null);
                            e.printStackTrace();
                        }
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }


        };
        requestQueue.add(postRequest);
    }

    public void getUserList(final Context context, @Nullable ServiceResponseInterface serviceResponseInterface, String url) {
        final ServiceResponseInterface listener =  serviceResponseInterface;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String s) {
                        if(listener !=null) {
                            response =  getModelFromJSON(response, s);
                            listener.OnSuccessResponse(response);
                        }
                        Log.d("Response", s);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        try {
                            if(listener !=null)
                                listener.OnErrorResponse(getModelFromJSON(response, error.toString()));
                            Log.d("Error.Response", error.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer "+ getJwt(context));
                return params;
            }
        };
        requestQueue.add(postRequest);
    }
}
