<template>
  <tr>
    <td>[공지]</td>
    <router-link :to="{ name: 'boardDetail', params: { articleno: bno } }">{{
      btitle
    }}</router-link>
    <td>{{ bwriter }}</td>
    <td>{{ bregtime }}</td>
    <td>{{ breadCount }}</td>
  </tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardNoticeListItem",
  props: {
    bno: Number,
    bwriter: String,
    btitle: String,
    bcontent: String,
    breadCount: Number,
    bregtime: String,
    bnotice: Boolean,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
