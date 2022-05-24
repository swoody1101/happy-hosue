<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
        <form action="">
          <label for="member-id">ID : </label>
          <input
            type="text"
            id="member-id"
            v-model="member.userid"
            required
            placeholder="아이디를 입력하세요"
          />

          <button>중복체크</button><br />
          <label for="member-pwd">PW : </label>
          <input
            type="password"
            id="member-pwd"
            v-model="member.userpwd"
            required
            placeholder="비밀번호를 입력하세요"
          /><br />
          <label for="pwd-check">PW 확인 : </label>
          <input
            type="text"
            id="member-pwd-check"
            v-model="memberpwdcheck"
            required
            placeholder="같은 비밀번호 입력"
          /><br />
          <label for="member-nick">닉네임 : </label>
          <input
            type="text"
            id="member-name"
            v-model="member.username"
            required
            placeholder="닉네임을 입력하세요"
          /><br />
          <label for="member-email">email : </label>
          <input
            type="text"
            id="member-email"
            v-model="member.email"
            required
            placeholder="email를 입력하세요"
          />
          <label for="member-email-check">인증코드 : </label>
          <input
            type="text"
            id="member-email-check"
            v-model="memberemailcheck"
            required
            placeholder="인증코드를 입력하세요"
          />
          <br />
          <button>인증코드 전송</button>
          <button>인증</button>
          <br />
          <button @click="registButton()">가입완료</button>
        </form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { join } from "@/api/member.js";
const memberStore = "memberStore";

export default {
  name: "MemberRegister",
  data() {
    return {
      memberpwdcheck: null,
      memberemailcheck: null,
      member: {
        userid: null,
        userpwd: null,
        username: null,
        email: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, [("isLogin", "isLoginError")]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.member);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.moveHomePage();
      }
    },
    moveHomePage() {
      this.$router.push({ name: "home" });
    },
    registButton() {
      join(this.member, this.func1, this.func2);
    },
    func1() {
      let msg = "success";
      alert(msg);
      //   왜 안가지지
      this.$router.push({ name: "singin" });
    },
    func2(error) {
      alert("error");
      console.log(error);
    },
  },
};
</script>

<style></style>
