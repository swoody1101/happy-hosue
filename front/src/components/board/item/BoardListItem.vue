<template>
  <tr>
    <td>{{ bno }}</td>
    <td class="article-title">
      <router-link :to="{ name: 'boardDetail', params: { articleno: bno } }">{{
        btitle
      }}</router-link>
    </td>
    <td>{{ bwriter }}</td>
    <td>{{ bregtime }}</td>
    <td>{{ breadCount }}</td>
  </tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    bno: Number,
    bwriter: String,
    btitle: String,
    breadCount: Number,
    bregtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style scoped>
td {
  padding: 15px 20px;
}
.article-title {
  text-align: left;
}
</style>
