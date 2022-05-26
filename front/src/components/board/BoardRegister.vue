<template>
  <div>
    <board-input-item type="register" />
  </div>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem.vue";
export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

.title {
  text-align: left;
  font-family: "Noto Sans KR", sans-serif;
}
</style>
