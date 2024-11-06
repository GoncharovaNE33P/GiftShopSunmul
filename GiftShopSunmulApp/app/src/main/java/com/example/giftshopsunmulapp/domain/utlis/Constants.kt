package com.example.giftshopsunmulapp.domain.utlis

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object Constants {
    val  supabase = createSupabaseClient(
        supabaseUrl = "https://eayauhuvkuvtbcqcwclq.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImVheWF1aHV2a3V2dGJjcWN3Y2xxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mjk2MDAzMDIsImV4cCI6MjA0NTE3NjMwMn0.5JQNGp6eREEfkmbU-y2G3UP_u7qbp5uDZZudTvDFWzc"
    )
    {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }
}