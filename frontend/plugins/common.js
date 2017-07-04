import lscache from 'lscache'
import cookies from 'js-cookie'

function getSessionId () {
  // 同步sessionId cookie
  const sessiondIdKey = 'session_id'
  let sessionId = lscache.get(sessiondIdKey) || cookies.get(sessiondIdKey)
  if (sessionId) {
    cookies.set(sessiondIdKey, sessionId)
    lscache.set(sessiondIdKey, sessionId)
  }
  return sessionId
}
function toParamString (param) {
  let arr = []
  for (const k of Object.keys(param)) {
    if (!param[k]) continue
    arr.push(`${k}=${param[k]}`)
  }
  return arr.join('&')
}

export default {getSessionId, toParamString}
