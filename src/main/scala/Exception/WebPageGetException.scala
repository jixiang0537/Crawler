package Exception

/**
 * Created by dell on 2016/1/14.
 */
class WebPageGetException(val code: String,val uri:String) extends Exception("Web Page Can't Get By Code : " + code) {}
