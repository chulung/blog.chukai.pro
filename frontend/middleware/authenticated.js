export default function ({store, redirect, route}) {
  if (process.BROWSER_BUILD && !store.state.isAuthenticated) {
    let path = route.path === '/cms/login' ? '' : route.path
    return redirect(`/cms/login?path=${path}`)
  }
}
