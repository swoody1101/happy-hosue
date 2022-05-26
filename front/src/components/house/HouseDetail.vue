<template>
  <b-container v-if="house" class="bv-example-row">
    <ul>
      <li>거래번호 : {{ h.dealNo }}</li>
      <li>주소 : {{ h.address }}</li>
      <li>면적 : {{ h.area }}</li>
      <li>층수 : {{ h.floor }}층</li>
      <li>건축일 : {{ h.dealDate }}</li>
    </ul>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  data() {
    return {
      h: null,
    };
  },
  created() {
    this.h = this.$store.state.houseStore.house;
  },
};
</script>

<style></style>
