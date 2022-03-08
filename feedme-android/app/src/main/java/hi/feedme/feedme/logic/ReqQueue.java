package hi.feedme.feedme.logic;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ReqQueue {
    private RequestQueue requestQueue;
    private static Context ctx;

    ReqQueue(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public synchronized ReqQueue getInstance() {
        return this;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}