export default function ({store, redirect, route}) {
  if (process.browser && !store.state.isAuthenticated) {
    let path = route.path === '/cms/login' ? '' : route.path
    return redirect(`/cms/login?path=${path}`)
  }
}
