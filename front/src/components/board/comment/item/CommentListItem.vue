<template>
  <tbody>
    <tr>
      <th>{{ cwriter }}</th>
      <td>{{ ccontent }}</td>
      <td>{{ cregtime }}</td>
      <td>
        <button @click="commentForm" class="btn">답글작성</button>
      </td>
      <td>
        <button
          @click="modifyForm"
          v-if="cwriter == userInfo.userid"
          class="btn"
        >
          수정
        </button>
      </td>
      <td>
        <button
          @click="deleteComment"
          v-if="cwriter == userInfo.userid || userInfo.role == 'ROLE_ADMIN'"
          class="btn"
        >
          삭제
        </button>
      </td>
    </tr>
    <tr v-if="reply">
      <td>
        <form>
          <label for="c">댓글</label>
          <input type="text" v-model="comment" id="c" />
          <button @click.prevent="onSubmit">등록</button>
        </form>
      </td>
    </tr>
    <tr v-if="modify">
      <td>
        <form>
          <label for="m">수정</label>
          <input type="text" v-model="mComment" id="m" />
          <button @click.prevent="onModify">등록</button>
        </form>
      </td>
    </tr>
  </tbody>
</template>

<script>
import { deleteComment, writeComment, modifyComment } from "@/api/comment";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "CommentListItem",
  props: {
    cno: Number,
    bno: Number,
    cdepth: Number,
    cwriter: String,
    ccontent: String,
    cregtime: String,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      reply: false,
      modify: false,
      comment: "",
      mComment: "",
    };
  },
  methods: {
    commentForm() {
      this.reply = !this.reply;
      console.log(this.reply);
    },
    modifyForm() {
      this.modify = !this.modify;
      console.log(this.modify);
    },
    deleteComment() {
      deleteComment(
        this.cno,
        () => {
          this.$emit("refresh");
        },
        (error) => {
          console.log(error);
        },
      );
    },
    onSubmit() {
      let err = true;
      let msg = "";
      !this.comment && ((msg = "댓글을 입력해주세요"), (err = false));

      if (!err) alert(msg);
      else this.registComment();
    },
    onModify() {
      let err = true;
      let msg = "";
      !this.mComment && ((msg = "댓글을 입력해주세요"), (err = false));

      if (!err) alert(msg);
      else this.modifyComment();
    },
    registComment() {
      console.log("userInfo", this.userInfo.userid);
      writeComment(
        this.cno,
        {
          bno: this.bno,
          ccontent: this.comment,
          cwriter: this.userInfo.userid,
        },
        () => {
          this.comment = "";
          this.commentForm();
          this.$emit("refresh");
        },
        (error) => {
          console.log(error);
        },
      );
    },
    modifyComment() {
      console.log("u", this.userInfo.userid);
      modifyComment(
        {
          cno: this.cno,
          bno: this.bno,
          cdepth: this.cdepth,
          ccontent: this.mComment,
          cwriter: this.userInfo.userid,
        },
        () => {
          this.mComment = "";
          this.modifyForm();
          this.$emit("refresh");
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style scoped></style>
