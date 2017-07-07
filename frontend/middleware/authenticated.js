export default function ({store, redirect}) {
  if (process.BROWSER_BUILD && !store.state.isAuthenticated) {
    return redirect('/cms/login?refer=' + window.location.href)
  }
}
