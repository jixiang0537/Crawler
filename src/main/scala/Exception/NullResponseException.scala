package Exception

import com.landchina

/**
 * Created by dell on 2016/1/19.
 */
class NullResponseException(getUri: String) extends Exception{
  landchina.lcAr+=getUri
}
