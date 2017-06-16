import lscache from 'lscache'
import cookies from 'js-cookie'

//同步sessionId cookie
const sessiondIdKey = 'session_id';
let sessionId = lscache.get(sessiondIdKey)
if (sessionId) {
  cookies.set(sessiondIdKey, sessionId)
} else {
  lscache.set(sessiondIdKey, cookies.get(sessiondIdKey));
}
