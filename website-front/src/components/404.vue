<template>
  <div id="content" class="site-content">
    <div id="primary" class="content-area">
      <main id="main" class="site-main" role="main">

        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <section class="error-404 not-found">
                <header class="page-header">
                  <h1 class="page-title">Oops! That page can’t be found.</h1>
                </header><!-- .page-header -->

                <div class="page-content text-center">
                  <p>It looks like nothing was found at this location. Maybe you can try one of another keyword to search</p>

                  <form role="search" method="get" class="search-form" action="/search">
                    <label>
                      <span class="screen-reader-text">Search for:</span>
                      <input class="search-field" placeholder="CSearch …" value="" name="word" type="search" required="required">
                    </label>
                    <input class="search-submit" value="Search" type="submit">
                  </form>

                </div><!-- .page-content -->
              </section><!-- .error-404 -->
            </div><!-- .col-md-12-->
          </div><!-- .row -->
        </div><!-- .container -->

      </main><!-- #main -->
    </div><!-- #primary -->
  </div>
</template>
