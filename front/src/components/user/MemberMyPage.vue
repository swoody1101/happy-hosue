<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page</template>

          <template #lead> 내 정보 확인페이지입니다. </template>

          <hr class="my-4" />

          <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userid }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.username
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button variant="primary" class="mr-1" @click="moveModifyPage"
            >회원정보 수정</b-button
          >
          <b-button variant="danger" @click="secession">회원탈퇴</b-button>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import { secession } from "@/api/member.js";
import { mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    secession() {
      let pw = prompt("정말로 삭제하시려면 비밀번호를 입력해주세요");
      this.user.userid = this.$store.state.memberStore.userInfo.userid;
      this.user.userpwd = pw;
      console.log(this.user);
      secession(
        this.$store.state.memberStore.userInfo.userid,
        pw,
        () => {
          this.logout();
          alert("회원탈퇴가 완료되었습니다");
          this.$router.push({ name: "home" });
        },
        (error) => {
          console.log(error);
        },
      );
    },
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    logout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      //   if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
    moveModifyPage() {
      this.$router.push({ name: "memberModify" });
    },
  },
};
</script>

<style></style>
