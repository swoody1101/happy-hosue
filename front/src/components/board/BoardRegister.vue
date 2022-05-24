<template>
  <div>
    <h2>글작성</h2>
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

<style></style>
