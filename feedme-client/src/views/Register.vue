<template>
    <v-card
        class="mx-auto"
        width="400"
        style="margin-top: 1rem;"
    >
    <v-card-title>FeedMe Register</v-card-title>
    <v-card-text>
        <v-form>
            <v-text-field
            v-model="username"
            label="Username"
            prepend-inner-icon="mdi-account"
            ></v-text-field>
            <v-text-field
            v-model="password"
            label="Password"
            prepend-inner-icon="mdi-lock"
            type="password"
            ></v-text-field>
            <v-btn
            color="orange darken-1"
            class="mr-4"
            width="100%"
            >
            Register
            </v-btn>          
        </v-form>
    </v-card-text>
    </v-card>
</template>

<script>
export default ({
    name: 'Register',
    data() {
        return {
            username: "",
            password: ""
        }
    },
    async mounted() {

    }
})
</script>
