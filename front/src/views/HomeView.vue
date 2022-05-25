<template>
  <div class="container">
    <main-logo></main-logo>
    <main-nav></main-nav>
  </div>
</template>

<script>
import MainNav from "@/components/index/MainNav.vue";
import MainLogo from "@/components/index/MainLogo.vue";

export default {
  name: "HomeView",
  components: {
    MainLogo,
    MainNav,
  },
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
}

/* 전체 가운데 정렬 해주기 */
.container {
  margin: auto;
  min-width: 1000px;
  max-width: 1400px;
}
</style>
