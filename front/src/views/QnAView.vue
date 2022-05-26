<template>
  <div>
    <h3 class="title">
      <b-icon icon="patch-question-fill"></b-icon> Q&A 게시판
    </h3>

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  created() {
    console.log("qna");
  },
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
