package Exception

/**
 * Created by dell on 2016/1/14.
 */
class WebPageGetException(val code: String) extends Exception("Web Page Can't Get By Code : " + code) {}
