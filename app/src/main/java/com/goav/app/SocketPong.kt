
import com.goav.netty.message.MessageBasic
import com.google.gson.annotations.SerializedName

/**
 * Created by moo on 20/10/2017.
 */
class SocketPong : MessageBasic() {

    @SerializedName("_method_")
    private val method: String = "pong"

    private val device: String = "android_kotlin";
}