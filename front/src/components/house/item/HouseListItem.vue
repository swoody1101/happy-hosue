<template>
  <div class="house-item-container">
    <div
      class="m-2"
      @click="selectHouse"
      @mouseover="colorChange(true)"
      @mouseout="colorChange(false)"
      :class="{ 'mouse-over-bgcolor': isColor }"
    >
      <div class="house-item-title">
        [ {{ house.dealNo }} ] {{ house.aptName }}
      </div>
      <br />
      <div>
        <div class="house-item-content">
          거래금액 :
          {{ (parseInt(house.price.replace(",", "")) * 10000) | price }}원
        </div>
        <div class="house-item-content">거래일 : {{ house.dealDate }}</div>
      </div>
      <div v-if="detail" class="detail-container">
        <house-detail></house-detail>
      </div>
    </div>
  </div>
</template>

<script>
import HouseDetail from "@/components/house/HouseDetail.vue";
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  components: { HouseDetail },
  name: "HouseListItem",
  data() {
    return {
      detail: false,
      isColor: false,
    };
  },
  props: {
    house: {},
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    components: {
      HouseDetail,
    },
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detail = !this.detail;
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");
.mouse-over-bgcolor {
  background-color: lightblue;
}
.house-item-container {
  border: 1px solid rgb(195, 195, 195);
  /* width: 50%; */
}
.house-item-title {
  font-size: 22px;
  font-weight: bold;
  margin-left: 10px;
  text-align: left;
  font-family: "Noto Sans KR", sans-serif;
  margin-bottom: -10px;
}
.house-item-content {
  margin-left: 25px;
  font-size: 18px;
  margin-bottom: 10px;
}
</style>
