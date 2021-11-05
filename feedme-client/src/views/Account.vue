<template>
    <h3>Hello {{user.username}}</h3>
</template>

<script>
import { getUserInfo } from '../service/userapi'
export default ({
    name: 'Account',
    data() {
        return {
            user: {}
        }
    },
    methods: {

    },
    async mounted() {
        const m = await getUserInfo(this.$store.state.token);
        this.user = m.data.user;
    }
})
</script>
