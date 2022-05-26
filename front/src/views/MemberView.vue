<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="title">
      <b-icon icon="person-lines-fill"></b-icon> 회원 페이지
    </h3>
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "MemberView",
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap");

.title {
  font-family: "Black Han Sans", sans-serif;
  font-size: 50px;
  text-align: left;
  margin: 40px;
}
</style>
