<template>
  <b-tr>
    <b-td>{{ qna_no }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'qnaDetail', params: { qna_no: qna_no } }"
        >{{ title }}
      </router-link>
    </b-th>
    <b-td>{{ content }}</b-td>
    <b-td>{{ writer }}</b-td>
  </b-tr>
</template>

<script>
export default {
  name: "QnaListItem",
  props: {
    qna_no: Number,
    title: String,
    writer: String,
    content: String,
  },
};
</script>

<style></style>
