package hr.fer.nox.userlib.util

import com.facebook.GraphRequest
import com.facebook.GraphResponse
import org.json.JSONObject

class EmptyGraphJSONObjectCallback: GraphRequest.GraphJSONObjectCallback {

    override fun onCompleted(`object`: JSONObject?, response: GraphResponse?) {

    }
}