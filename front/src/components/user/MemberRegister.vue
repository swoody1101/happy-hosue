<template>
  <b-container class="bv-example-row mt-3 container">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
        <div class="regist-container">
          <form @submit="registButton" class="regist-form">
            <b-form-group>
              ID:
              <b-form-input
                type="text"
                id="userid"
                v-model="member.userid"
                required
                placeholder="아이디를 입력하세요"
                class="input"
                @input="idchange"
              />
              <b-button @click="formIdCheck">중복체크</b-button>
              <span v-if="idcheck" style="color: green; font-size: small"
                >사용 가능한 ID</span
              >
              <span v-if="idcheckfalse" style="color: red; font-size: small"
                >이미 존재하는 아이디입니다!!</span
              >
            </b-form-group>
            <b-form-group>
              PW:
              <b-form-input
                type="password"
                id="member-pwd"
                v-model="member.userpwd"
                required
                placeholder="비밀번호를 입력하세요"
                class="input"
                aria-required="true"
              />
            </b-form-group>
            <b-form-group>
              PW 확인:
              <b-form-input
                type="password"
                id="member-pwd-check"
                v-model="memberpwdcheck"
                required
                placeholder="같은 비밀번호 입력"
                class="input"
                aria-required="true"
              />
              <span
                v-if="member.userpwd == memberpwdcheck"
                style="color: green; font-size: small"
                >비밀번호 확인 완료</span
              >
              <span
                v-if="member.userpwd != memberpwdcheck"
                style="color: red; font-size: small"
                >서로 다른 비밀번호</span
              >
            </b-form-group>
            <b-form-group>
              닉네임:
              <b-form-input
                type="text"
                id="member-name"
                v-model="member.username"
                required
                placeholder="닉네임을 입력하세요"
                class="input"
                aria-required="true"
              />
            </b-form-group>
            <b-form-group>
              이메일:
              <b-form-input
                type="text"
                id="member-email-id"
                v-model="emailId"
                required
                placeholder="아이디"
                class="email"
                aria-required="true"
              />

              @
              <b-form-input
                type="text"
                id="member-email-address"
                v-model="emailAddress"
                required
                placeholder="naver.com"
                class="email"
                aria-required="true"
              />
              <b-button @click="emailConfirmBtn">인증코드 전송</b-button>
            </b-form-group>
            <b-form-group>
              인증코드:
              <b-form-input
                type="text"
                id="member-email-check"
                v-model="memberemailcheck"
                required
                placeholder="인증코드를 입력하세요"
                class="input"
                aria-required="true"
              />
              <b-button @click="emailCheck">인증</b-button>
              <span v-if="emailcheck" style="color: green; font-size: small"
                >이메일 인증 완료</span
              >
            </b-form-group>

            <b-button type="submit" class="regist-btn" variant="success"
              >가입완료</b-button
            >
          </form>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { join, idCheck, sendEmailCode } from "@/api/member.js";
const memberStore = "memberStore";

export default {
  name: "MemberRegister",
  data() {
    return {
      idcheck: false,
      idcheckfalse: false,
      memberpwdcheck: null,
      memberemailcheck: null,
      emailcheck: false,
      emailConfirmCode: null,
      emailId: null,
      emailAddress: null,
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
    formIdCheck() {
      console.log(this.member.userid);
      idCheck(
        this.member.userid,
        (data) => {
          console.log("ID체크완료");
          if (data.data) {
            this.idcheck = true;
            this.idcheckfalse = false;
          } else {
            this.idcheck = false;
            this.idcheckfalse = true;
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    idchange() {
      this.idcheck = false;
      this.idcheckfalse = false;
    },
    emailConfirmBtn() {
      sendEmailCode(
        this.emailId,
        this.emailAddress,
        (data) => {
          this.emailConfirmCode = data.data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
    emailCheck() {
      if (this.memberemailcheck == this.emailConfirmCode) {
        this.emailcheck = true;
      }
    },
    registButton(event) {
      event.preventDefault();

      if (!this.idcheck) {
        alert("ID 중복체크를 완료해주세요");
      } else if (!this.memberpwdcheck) {
        alert("비밀번호를 확인해주세요");
      } else if (!this.emailcheck) {
        alert("이메일 인증을 완료해주세요");
      } else {
        this.member.email = this.emailId + "@" + this.emailAddress;
        join(
          this.member,
          () => {
            this.$router.push({ name: "signIn" });
          },
          (error) => {
            console.log(error);
          },
        );
      }
    },
  },
};
</script>

<style>
.regist-btn {
  background-color: rgb(65, 100, 255);
  border: none;
}
.input {
  width: 30%;
  display: inline;
  margin-right: 10px;
}

.email {
  width: 20%;
  display: inline;
  margin-right: 10px;
}

.regist-container {
  text-align: center;
  /* width: 100px; */
}

.regist-form {
  text-align: center;

  /* width: max-content; */
}
</style>
