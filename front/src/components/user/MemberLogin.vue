<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="confirm"
              >로그인</b-button
            >
            <b-button
              type="button"
              variant="success"
              class="m-1"
              @click="movePage"
              >회원가입</b-button
            >
            <b-button type="button" class="m-1" @click="findPwd"
              >비밀번호를 잊으셨나요?</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { newPwd, idCheck, getEmail } from "@/api/member.js";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "signup" });
    },
    findPwd() {
      let id = prompt("비밀번호를 찾을 ID를 입력해주세요");
      idCheck(
        id,
        (data) => {
          if (data.data) {
            alert("존재하지 않는 id입니다!");
          } else {
            console.log(id);
            let email = null;
            getEmail(
              { userid: id, userpwd: null, username: null, email: null },
              (data) => {
                console.log(data.data);
                email = data.data;
                console.log("email:" + email);
                alert(
                  "해당 아이디의 이메일 주소(" +
                    email +
                    ")로 새로운 비밀번호를 발급하였습니다. 새로운 비밀번호로 로그인해주세요",
                );
                newPwd(
                  id,
                  email,
                  () => {},
                  () => {},
                );
              },
              (error) => {
                console.log(error);
              },
            );
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style></style>
