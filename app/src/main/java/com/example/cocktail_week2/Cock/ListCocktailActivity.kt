package com.example.cocktail_week2.Cock

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktail_week2.MainActivity
import com.example.cocktail_week2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListCocktailActivity : AppCompatActivity() {

    private lateinit var cocktailAdapter: CocktailAdapter  // 칵테일 어댑터
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var fabGoToMain: FloatingActionButton

    // 임시 데이터 리스트, 실제 앱에서는 데이터베이스 또는 API에서 가져올 것
    private var cocktailList = mutableListOf<Cocktail>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cocktail)
        supportActionBar?.hide()

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        fabGoToMain = findViewById(R.id.fabGoToMain)

        // RecyclerView 설정
        recyclerView.layoutManager = LinearLayoutManager(this)
        cocktailAdapter = CocktailAdapter(cocktailList)
        recyclerView.adapter = cocktailAdapter

        // 예시 칵테일 데이터 생성 및 리스트에 추가
        cocktailList.add(
            Cocktail(
                name = "마티니",
                type = "클래식",
                description = "진과 드라이 베르무트로 만든 칵테일",
                imageResourceId = R.drawable.cocktail1  // 적절한 이미지 리소스로 변경
            )
        )
        cocktailList.add(
            Cocktail(
                name = "모히토",
                type = "리프레시",
                description = "럼, 라임, 민트 잎으로 만든 상큼한 칵테일",
                imageResourceId = R.drawable.cocktail2  // 적절한 이미지 리소스로 변경
            )
        )
        cocktailList.add(
            Cocktail(
                name = "마가리타",
                type = "소금 림",
                description = "데킬라, 라임, 오렌지 리큐어로 만든 칵테일",
                imageResourceId = R.drawable.cocktail2  // 적절한 이미지 리소스로 변경
            )
        )

        // 검색 기능 설정
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색 버튼이 눌렸을 때 호출됨
                query?.let { searchCocktails(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // 텍스트가 바뀔 때마다 호출됨
                newText?.let { searchCocktails(it) }
                return false
            }
        })

        // FloatingActionButton 설정 - 메인 화면으로 이동
        fabGoToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // 칵테일 검색 기능
    private fun searchCocktails(query: String) {
        val filteredList = cocktailList.filter {
            it.name.contains(query, ignoreCase = true)  // 이름으로 필터링, 대소문자 구분 안 함
        }
        cocktailAdapter.updateList(filteredList)  // 어댑터에 변경된 리스트 반영
    }
}

// 칵테일 데이터 클래스
data class Cocktail(val name: String, val type: String, val description: String, val imageResourceId: Int)

// 칵테일 어댑터
class CocktailAdapter(private var items: List<Cocktail>) : RecyclerView.Adapter<CocktailAdapter.ViewHolder>() {
    // 뷰 홀더, 아이템 레이아웃을 로드하여 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cocktail_item, parent, false)
        return ViewHolder(view)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    // 아이템 개수 반환
    override fun getItemCount() = items.size

    // 리스트 업데이트 함수
    fun updateList(newItems: List<Cocktail>) {
        items = newItems
        notifyDataSetChanged()  // 데이터가 변경됨을 알림
    }

    // 뷰 홀더 클래스
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(cocktail: Cocktail) {
            // 칵테일 이미지 설정
            val imageView: ImageView = view.findViewById(R.id.imageViewCocktail)
            imageView.setImageResource(cocktail.imageResourceId) // 이미지 리소스 ID를 사용하여 이미지 설정

            // 칵테일 이름 설정
            val nameTextView: TextView = view.findViewById(R.id.textViewName)
            nameTextView.text = cocktail.name  // 칵테일 이름 설정

            // 칵테일 종류 설정
            val typeTextView: TextView = view.findViewById(R.id.textViewType)
            typeTextView.text = cocktail.type  // 칵테일 종류 설정

            val descriptionTextView: TextView = view.findViewById(R.id.textViewDescription)
            descriptionTextView.text = cocktail.description
            // 기타 정보를 추가하고 싶다면 여기에 코드를 추가하세요.
        }
    }
}
