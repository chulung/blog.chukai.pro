<template>
  <article class="post format-standard hentry">
  <div class="post-container">
    <div class="post-content">
      <div v-for="article in articles"><router-link :to="'/article/'+article.uri" rel="bookmark">{{article.title}}</router-link></div>
    </div>
  </div>
  </article>
</template>
<script>
  export default{
    props:['articles']
  }
</script>
