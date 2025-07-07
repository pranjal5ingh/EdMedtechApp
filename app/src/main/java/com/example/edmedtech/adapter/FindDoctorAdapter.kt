import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edmedtech.databinding.ItemsFindDoctorBinding
import com.example.edmedtech.models.Doctor

class DoctorAdapter(private val doctors: MutableList<Doctor>) :
    RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    inner class DoctorViewHolder(val binding: ItemsFindDoctorBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val binding = ItemsFindDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.binding.imgDoctor.setImageResource(doctor.imageResId)
        holder.binding.txtDoctorName.text = doctor.name
        holder.binding.txtDoctorDesc.text = doctor.description
        holder.binding.txtSpecialization.text = doctor.specialization
        holder.binding.txtExperience.text = doctor.experience
    }

    override fun getItemCount() = doctors.size

    fun updateList(newList: List<Doctor>) {
        doctors.clear()
        doctors.addAll(newList)
        notifyDataSetChanged()
    }
}
