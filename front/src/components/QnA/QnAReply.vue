<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글작성</h3></b-alert>
      </b-col>
    </b-row>
    <qna-reply-item type="register" />
  </b-container>
</template>

<script>
import QnaReplyItem from "@/components/QnA/item/QnaReplyItem.vue";

export default {
  name: "QnAReply",
  components: {
    QnaReplyItem,
  },
};
</script>

<style></style>
