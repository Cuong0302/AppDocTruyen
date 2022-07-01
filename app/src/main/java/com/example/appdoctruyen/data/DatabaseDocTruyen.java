package com.example.appdoctruyen.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appdoctruyen.model.TaiKhoan;
import com.example.appdoctruyen.model.Truyen;

public class DatabaseDocTruyen extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "Truyen.db";
    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";
    private static int VERSION = 1;

    private static String TABLE_TRUYEN = "truyen";
    private static String ID_TRUYEN = "idtruyen";
    private static String TEN_TRUYEN = "tieude";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";
    private static String SO_LUOT_XEM = "soluotxem";

    private Context context;

    private String SQLQuery = "CREATE TABLE "+ TABLE_TAIKHOAN +" ( "+ID_TAI_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TAI_KHOAN+" TEXT UNIQUE, "
            +MAT_KHAU+" TEXT, "
            +EMAIL+" TEXT, "
            + PHAN_QUYEN+" INTEGER) ";

    private String SQLQuery1 = "CREATE TABLE "+ TABLE_TRUYEN +" ( "+ID_TRUYEN+" integer primary key AUTOINCREMENT, "
            +TEN_TRUYEN+" TEXT UNIQUE, "
            +NOI_DUNG+" TEXT, "
            +IMAGE+" TEXT, "
            +SO_LUOT_XEM +" INTEGER, "
            +ID_TAI_KHOAN+" INTEGER , FOREIGN KEY ( "+ ID_TAI_KHOAN +" ) REFERENCES "+
            TABLE_TAIKHOAN+"("+ID_TAI_KHOAN+"))";


    private String SQLQuery2 = "INSERT INTO TaiKhoan VAlUES (null,'admin','admin','admin@gmail.com',2)";

    private String SQLQuery4 = "INSERT INTO "+TABLE_TRUYEN+" VALUES (null,'Rùa và Thỏ','Phần 1:\n" +
            "\n" +
            "Ngày xửa ngày xưa, có một con Rùa và một con Thỏ sống trong một khu rừng xinh đẹp và yên tĩnh. Ngày ngày chúng vui chơi với nhau như hai người bạn thân. Một hôm, Thỏ và Rùa cãi nhau xem ai nhanh hơn. Rồi chúng quyết định giải quyết việc tranh luận bằng một cuộc thi chạy đua. Thỏ và Rùa đồng ý lộ trình và bắt đầu cuộc đua. Thỏ xuất phát nhanh như tên bắn và chạy thục mạng rất nhanh, khi thấy rằng mình đã khá xa Rùa, Thỏ nghĩ nên nghỉ cho đỡ mệt dưới một bóng cây xum xuê lá bên vệ đường. Vì quá tự tin vào khả năng giành chiến thắng của mình, Thỏ ngồi dưới bóng cây và nhanh chóng ngủ thiếp đi. Rùa chạy mãi rồi cũng đến nơi, thấy Thỏ đang ngủ ngon giấc Rùa từ từ vượt qua Thỏ và về đích trước Thỏ. Khi Thỏ thức dậy thì rùa đã đến đích và trở thành người chiến thắng. Lúc này Thỏ biết mình đã thua cuộc vì quá tự tin vào khả năng của mình, còn Rùa chiến thắng vì kiên trì bám đuổi mục tiêu và làm việc hết sức trong khả năng của mình, cộng với một chút may mắn và giành chiến thắng.\n" +
            "\n" +
            "Ý nghĩa câu chuyện phần 1: truyện giáo dục đức tính kiên trì, siêng năng, nhẫn nại. Những người nhanh nhẹn nhưng cẩu thả trong suy nghĩ cuối cùng cũng sẽ bị đánh bại bởi người kiên nhẫn, siêng năng dù họ chậm hơn rất nhiều.\n" +
            "\n" +
            "Phần 2:\n" +
            "\n" +
            "Thỏ vô cùng thất vọng vì để thua Rùa, nó nhận ra rằng nó thua chính vì quá tự tin, bất cẩn và thiếu kỷ luật. Nếu nó không xem mọi thứ quá dễ dàng và chắc thắng thì rùa không thể có cơ hội hạ được nó. Vì thế, Thỏ quyết định thách thức Rùa bằng một cuộc đua mới. Rùa đồng ý. Lần này, Thỏ chạy với tất cả sức lực của nó và chạy một mạch về đích. Nó bỏ xa Rùa đến mấy dặm đường.\n"+
            "Ý nghĩa câu chuyện phần 2: Biết sai và sửa sai là một đức tính tốt, đó chính là lý do giúp anh chàng thỏ giành được chiến thắng ở cuộc đua thứ 2. Mẹ hãy giải thích cho bé hiểu rằng trong công việc hàng ngày giữa một người chậm, cẩn thận và đáng tin cậy với một người nhanh nhẹn, đáng tin cậy, chắc chắn người nhanh nhẹn sẽ được trọng dụng hơn nhiều và họ sẽ tiến xa hơn trong học tập, cũng như trong cuộc sống. Cha mẹ hãy giúp bé hiểu rõ thông điệp chậm và chắc là điều tốt, nhưng nhanh và đáng tin cậy sẽ tốt hơn rất nhiều.','https://toplist.vn/images/800px/rua-va-tho-230179.jpg',0,1)";
    private String SQLQuery5 = "INSERT INTO "+TABLE_TRUYEN+" VALUES (null,'Củ cải trắng','Mùa đông đã đến rồi trời lạnh buốt, Thỏ con không có gì để ăn cả. Thỏ con mặc áo vào rồi ra ngoài kiếm thức ăn. Nó đi mãi đi mãi cuối cùng cũng tìm được hai củ cải trắng. Thỏ con reo lên:\n" +
            "\n" +
            "– Ôi, ở đây có hai củ cải trắng liền, mình thật là may mắn!\n" +
            "\n" +
            "Thỏ con đói bụng, muốn ăn lắm rồi. Nhưng Thỏ lại nghĩ:\n" +
            "\n" +
            "– Ừm… trời lạnh thế này, chắc Dê con cũng không có cái gì để ăn đâu. Mình phải mang cho Dê con một củ mới được.\n" +
            "\n" +
            "Thế là Thỏ con đi sang nhà bạn Dê nhưng Dê con không có nhà nên Thỏ đặt củ cải lên bàn rồi đi về.\n" +
            "\n" +
            "Tình cờ, Dê con đi chơi cũng tìm được một củ cải trắng nhưng nó chỉ ăn trước một nửa.\n" +
            "\n" +
            "Về đến nhà, lại thấy có một củ cải trắng ở trên bàn Dê thèm ăn lắm, nhưng lại nghĩ:\n" +
            "\n" +
            "– Ôi trời lạnh thế này chắc Hươu con không có cái gì để ăn rồi, mình phải mang cho Hươu con mới được.\n" +
            "\n" +
            "Dê con đến nhà Hươu nhưng Hươu lại đi vắng, Dê con bèn đặt củ cải ở trên bàn rồi về.\n" +
            "\n" +
            "Khi Hươu về nhà, thấy củ cải ở trên bàn, Hươu ngạc nhiên lắm.\n" +
            "\n" +
            "– Ồ, củ cải trắng ở đâu mà ngon vậy nhỉ. Xuỵt… thích quá. Nhưng chắc trời lạnh thế này, Thỏ con cũng không có gì ăn đâu. Mình phải mang sang cho Thỏ mới được.\n" +
            "\n" +
            "Khi Hươu đến thì Thỏ con đang ngủ rất say. Khi tỉnh dậy Thỏ lại thấy trên bàn mình xuất hiện một củ cải trắng.Thỏ vui lắm nó chạy đi gọi các bạn:\n" +
            "\n" +
            "– Bạn Hươu ơi, bạn Dê ơi hãy đến nhà tôi, chúng ta cùng ăn củ cải trắng thơm ngon này.\n" +
            "\n" +
            "Thế là cuối cùng, củ cải trắng ấy được chia sẻ cho cả ba người bạn tốt bụng của chúng ta. Các bạn thấy đấy tấm lòng thơm thảo, sẵn sàng sẻ chia của các bạn ấy thật là đáng học tập phải không nào?\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Khi cho đi bạn sẽ nhận lại được nhiều hơn những thứ mình có.','https://toplist.vn/images/800px/cu-cai-trang-230181.jpg',0,1)";
    private String SQLQuery6 = "INSERT INTO "+TABLE_TRUYEN+" VALUES (null,'Dê đen và dê trắng','Dê đen và Dê trắng cùng sống trong một khu rừng. Hàng ngày, cả hai thường đến uống nước và tìm cái ăn ở trong khu rừng quen thuộc.\n" +
            "\n" +
            "Một hôm, Dê trắng đi tìm cái ăn và uống nước suối như mọi khi. Dê đang mải mê ngặm cỏ, bất chợt một con Sói ở đâu nhảy xổ ra. Sói quát hỏi:\n" +
            "\n" +
            "- Dê kia! Mi đi đâu?\n" +
            "\n" +
            "Dê trắng sợ rúm cả người, lắp bắp:\n" +
            "\n" +
            "– Dạ, dạ, tôi đi tìm… tìm cỏ non và…và uống nước suối ạ!\n" +
            "\n" +
            "Sói lại quát hỏi:\n" +
            "\n" +
            "– Mi có gì ở chân?\n" +
            "\n" +
            "– Dạ, dạ, chân của tôi có móng ạ…ạ!\n" +
            "\n" +
            "– Trên đầu mi có gì?\n" +
            "\n" +
            "– Dạ, dạ, trên đầu tôi có đôi sừng mới nhú…\n" +
            "\n" +
            "Sói càng quát to hơn:\n" +
            "\n" +
            "– Trái tim mi thế nào?\n" +
            "\n" +
            "– Ôi, ôi, trái…trái tim tôi đang run sợ…sợ…\n" +
            "\n" +
            "– Hahaha…\n" +
            "\n" +
            "Sói cười vang rồi ăn thịt chú Dê trắng tội nghiệp\n" +
            "\n" +
            "Dê đen cũng đi tới khu rừng để ăn cỏ non và uống nước suối. Đang tha thẩn ngặm cỏ, chợt Sói xuất hiện, nó quát hỏi:\n" +
            "\n" +
            "– Dê kia, mi đi đâu?\n" +
            "\n" +
            "Dê đen nhìn con Sói từ đầu tới chân rồi ngước cổ trả lời:\n" +
            "\n" +
            "– Ta đi tìm kẻ nào thích gây sự đây!\n" +
            "\n" +
            "Sói bị bất ngờ, nó hỏi tiếp:\n" +
            "\n" +
            "– Thế dưới chân mi có gì?\n" +
            "\n" +
            "– Chân thép của ta có móng bằng đồng.\n" +
            "\n" +
            "– Thế…thế…trên đầu mi có gì?\n" +
            "\n" +
            "– Trên đầu của ta có đôi sừng bằng kim cương!\n" +
            "\n" +
            "Sói sợ lắm rồi, nhưng vẫn cố hỏi:\n" +
            "\n" +
            "– Mi…mi…trái tim mi thế nào?\n" +
            "\n" +
            "Dê đen dõng dạc trả lời:\n" +
            "\n" +
            "– Trái tim thép của ta bảo ta rằng: hãy cắm đôi sừng kim cương vào đầu Sói. Nào, Sói hãy lại đây.\n" +
            "\n" +
            "Ôi trời, sợ quá, con Sói ba chân bốn cẳng chạy biến vào rừng, từ đó không ai trông thấy nó lởn vởn ở khu rừng đó nữa.\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Qua câu chuyện ngụ ngôn trên, bạn có thể truyền tải nhiều thông điệp khác nhau cho bé hiểu. Chẳng hạn như biết cách ứng xử trước các tình huống khó, nguy hiểm, lạc quan và bản lĩnh để xử lý vấn đề.','https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg',0,1)";
    private String SQLQuery7 = "INSERT INTO "+TABLE_TRUYEN+" VALUES (null,'Chú bé chăn cừu','Một chú bé chăn cừu cho chủ thả cừu gần một khu rừng rậm cách làng không xa lắm. Chăn cừu được ít lâu, chú cảm thấy công việc chăn cừu thực là nhàm chán. Tất cả mọi việc chú có thể làm để giải khuây là nói chuyện với con chó hoặc thổi chiếc sáo chăn cừu của mình.\n" +
            "\n" +
            "Một hôm, trong lúc đang ngắm nhìn đàn cừu và cánh rừng yên tĩnh chú bé chợt nhớ tới lời chủ của chú từng dặn rằng khi sói tấn công cừu thì phải kêu cứu để dân làng nghe thấy và đánh đuổi nó đi.\n" +
            "\n" +
            "Thế là chú nghĩ ra một trò chơi cho đỡ buồn. Mặc dù chẳng thấy con sói nào, chú cứ chạy về làng và la to:\n" +
            "\n" +
            "– Sói ! Sói !\n" +
            "\n" +
            "Đúng như chú nghĩ, dân làng nghe thấy tiếng kêu bỏ cả việc làm và tức tốc chạy ra cánh đồng. Nhưng khi đến nơi, họ chẳng thấy sói đâu, chỉ thấy chú bé ôm bụng cười ngặt nghẽo vì đã lừa được họ.\n" +
            "\n" +
            "Ít ngày sau chú bé chăn cừu lần nữa lại la lên:\n" +
            "\n" +
            "– “Sói ! Sói !”.\n" +
            "\n" +
            "Và một lần nữa dân làng lại chạy ra giúp chú. Nhưng họ lại chẳng thấy con sói nào, chỉ thấy chú bé chăn cừu nghịch ngợm ôm bụng cười khoái chí.\n" +
            "\n" +
            "Thế rồi vào một buổi chiều nọ, khi mặt trời lặn xuống sau cánh rừng và bóng tối bắt đầu phủ đầy lên cánh đồng, một con sói thực sự xuất hiện. Nó nấp sau bụi cây rình bắt những con cừu béo non. Bỗng sói phóng vút ra chộp lấy một chú cừu tội nghiệp. Thấy sói cả đàn cừu sợ hãi chạy toán loạn, chú bé chăn cừu cũng hoảng loạn vô cùng.\n" +
            "\n" +
            "Quá hoảng sợ, chú bé chăn cừu vội chạy về làng và la to:\n" +
            "\n" +
            "– “Sói ! Sói !”.\n" +
            "\n" +
            "Nhưng mặc dù dân làng có nghe tiếng chú kêu, không một ai chạy ra để giúp chú như những lần trước cả. Vì mọi người nghĩ chú lại bày trò nói dối như trước.\n" +
            "\n" +
            "Thế là sói thỏa sức bắt mồi, giết chết rất nhiều cừu của chú bé. Sau khi đã chén no nê, nó biến mất vào rừng rậm. Chú bé buồn bã ngồi giữa đồng cỏ, lòng đầy hối hận về hành động nói dối của mình và hậu quả của trò đùa dại dột gây ra.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Nói dối là một tật xấu. Người hay nói dối ngay cả khi nói thật cũng không ai tin.','https://toplist.vn/images/800px/chu-be-chan-cuu-230183.jpg',0,1)";
    private String SQLQuery8 = "INSERT INTO "+TABLE_TRUYEN+" VALUES (null,'Cậu bé chăn cừu và cây đa cổ thụ','Ngày xửa ngày xưa, xưa lắm rồi khi mà muôn thú, cây cỏ, con người còn trò chuyện được với nhau. Trên đồng cỏ rậm ven khu làng có một loài cây gọi là cây đa. Đó là một thứ cây to, khỏe, lá của nó rậm rạp đến nỗi không một tia nắng nào có thể lọt qua được. Vào những ngày trời nắng nóng người ta thường nghỉ chân một lát và trò chuyện hàn huyên cùng cây dưới bóng cây mát rượi. Mọi người ai cũng biết rằng cây đa rất thông thái vì cây đã có tuổi, đã từng trải.\n" +
            "\n" +
            "\n" +
            "Một hôm, có một cậu bé chăn cừu ngồi nghỉ mát dưới gốc cây sau một ngày dài phơi mình dưới nắng cậu bé thấy người mệt mỏi và nóng bức. Một làn gió mơn man thổi thoa nhẹ lên tấm thân mỏi mệt của chú bé. Cậu bé bắt đầu thấy buồn ngủ. Vừa đặt mình xuống cậu bé bỗng ngước mắt nhìn lên những cành cây. Bấy giờ cậu bé bỗng thấy mình thật kiêu hãnh, cậu vẫn thường hay khoe với mọi người rằng cậu có tài chăn cừu và đàn cừu của cậu nhờ vậy mà lớn rất nhanh. Khi cậu bé phát hiện ra cây đa chỉ có những chùm quả rất nhỏ, nó bắt đầu thấy ngạc nhiên. Cậu bắt đầu chế giễu: hư, một cái cây to khỏe thế này mà làm sao chỉ có những bông hoa những chùm quả bé tí tẹo thế kia, mọi người vẫn bảo là cái cây này thông thái lắm kia mà nhưng làm sao nó có thể thông thái khi mà quả của nó chỉ toàn bé xíu như vậy. Dĩ nhiên là cây đa nghe hết những lời của cậu bé nhưng cây vẫn im lặng và cành lá chỉ khẽ rung rinh đủ để cho gió cất lên khúc hát ru êm dịu. Cậu bé bắt đầu ngủ, cậu ngáy o o…. Cốc.\n" +
            "Quả đa nhỏ rụng chính giữa trán của cậu bé, nó bừng tỉnh nhưng càu nhàu: “Gừm… người ta vừa mới chợp mắt được có một tí”, rồi nó nhặt quả đa lên chưa hết chưa biết định làm gì với quả đa này bỗng nhiên cậu bé nghe thấy có tiếng cười khúc khích, cậu nghe thấy cây hỏi:\n" +
            "– Có đau không ?.\n" +
            "– Không nhưng mà làm người ta mất cả giấc ngủ .\n" +
            "– Đó là bài học cho cậu bé to đầu đấy. Cậu chẳng vừa nhạo tôi là chỉ sinh ra toàn những quả nhỏ xíu là gì.\n" +
            "– Tôi nhạo đấy tại sao người đời lại bảo bác là thông thái được nhỉ? Phá giấc ngủ trưa của người khác! Thế cũng là thông minh chắc!.\n" +
            "Cây cười và nói: này này anh bạn anh hãy nghe đây những chiếc lá của tôi cho cậu bóng mát để cậu lấy chỗ nghỉ ngơi. Ừ thì cứ cho là quả của tôi nó bé đi chăng nữa nhưng chẳng lẽ cậu không thấy rằng tạo hóa hoạt động rất hoàn chỉnh đó sao. Cậu thử tưởng tượng xem, nếu quả của tôi to như quả dừa thì điều gì sẽ xảy ra khi nó rơi vào đầu cậu.\n" +
            "Cậu bé im thin thít: ừ nhở. Cậu chưa hề nghĩ đến điều này bao giờ cả.\n" +
            "Cây lại nhẹ nhàng tiếp lời:\n" +
            "– Những người khiêm tốn có thể học hỏi rất nhiều điều từ việc quan sát những vật xung quanh đấy cậu bé ạ.\n" +
            "– Vâng bác đa bác cứ nói tiếp đi.\n" +
            "– Cậu hãy bắt đầu làm bạn với những gì ở quanh cậu. Chúng ta tất cả đều cần tới nhau. Cậu cứ nhìn bầy ong kia mà xem. Nhờ có ong mà hoa của tôi mới có thể trở thành quả. Thế còn bầy chim kia thì sao. Chúng làm tổ ngay giữa tán lá của tôi đây này. Những con chim bố mẹ kia phải làm việc vất vả cả ngày để bắt sâu nuôi con và cậu có biết việc làm đó có ý nghĩa gì với tôi không?.\n" +
            "– Không, có ý nghĩa gì vậy hả bác?.\n" +
            "– Sâu ăn lá chính vậy loài chim kia chính là những người bạn của tôi. Chúng còn giúp cả cậu nữa đấy, sở dĩ cừu của cậu có đủ lá và cỏ để ăn là vì chim chóc đã tiêu diệt hết các loài côn trùng và sâu bọ. Và chưa hết đâu cậu bé ạ!.\n" +
            "– Còn gì thế nữa hả bác đa.\n" +
            "– Cậu hãy nhìn xuống chân mình mà xem, những chiếc lá rụng tạo thành lớp thảm mục, những con sâu đào đất ngoi lên để ăn lá, chúng đào đất thành những lỗ nhỏ, nhờ đó không khí có thể vào được trong đất. Có không khí trong đất nên bộ rễ của tôi mới khỏe thế nào đấy. Rễ khỏe nên tôi cũng khỏe hơn. Nào thế bây giờ cậu trẻ đã hiểu chưa?.\n" +
            "– Cháu hiểu rồi thưa bác. Bác tha lỗi cho cháu nhé vì đã cười nhạo bác bác đa ạ.\n" +
            "– Không sao bây giờ cháu hãy ra dắt cừu về đi.\n" +
            "\n" +
            "\n" +
            "Ý nghĩa câu chuyện: Có thể cậu bé chăn cừu không phải ngay sau đó sẽ trở nên khiêm tốn, học hỏi luôn được nhưng rõ ràng là cậu đã nhận ra người ta không thể sống lẻ loi được.','https://toplist.vn/images/800px/cau-be-chan-cuu-va-cay-da-co-thu-230184.jpg',0,1)";

    public DatabaseDocTruyen(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Truy vấn ko có trả về kq , Tạo bảng và insert
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery2);
        db.execSQL(SQLQuery4);
        db.execSQL(SQLQuery5);
        db.execSQL(SQLQuery6);
        db.execSQL(SQLQuery7);
        db.execSQL(SQLQuery8);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void AddTaiKhoan(TaiKhoan taiKhoan){
        SQLiteDatabase db = this.getWritableDatabase();

        //không thể lưu trực tiếp xuống insert nên thông qua contentvalues
        ContentValues values = new ContentValues();
        values.put(TEN_TAI_KHOAN,taiKhoan.getmTenTaiKhoan());
        values.put(MAT_KHAU,taiKhoan.getmMatKhau());
        values.put(EMAIL,taiKhoan.getmEmail());
        values.put(PHAN_QUYEN,taiKhoan.getmPhanQuyen());

        db.insert(TABLE_TAIKHOAN,null,values);
        //đóng lại db cho an toàn
        db.close();
        //Log.e("Add Tai Khoan ","thành công");
    }


    //Lấy tất cả tài khoản
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_TAIKHOAN , null );
        return res;
    }

    public void soLuotXem(int Soluotxem, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("TAG", "soLuotXem: " + Soluotxem);
        db.execSQL("UPDATE "+TABLE_TRUYEN+" SET "+SO_LUOT_XEM+" = "+Soluotxem+" WHERE "+ID_TRUYEN+"= "+id+"");
        db.close();
    }

    //Thêm truyện
    public void AddTruyen(Truyen truyen){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TEN_TRUYEN,truyen.getTenTruyen());
        values.put(NOI_DUNG,truyen.getNoiDung());
        values.put(IMAGE,truyen.getAnh());
        values.put(SO_LUOT_XEM, truyen.getSoluotxem());
        values.put(ID_TAI_KHOAN,truyen.getID_TK());

        db.insert(TABLE_TRUYEN,null,values);
        db.close();
        Log.e("Add Truyện : ","Thành công");
    }


    //Lấy tin mới nhất
    public Cursor getData1(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from "+TABLE_TRUYEN+" ORDER BY "+ID_TRUYEN , null );
        return res;
    }


    //Lấy tất cả truyện
    public Cursor getData2(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_TRUYEN,null);
        return res;
    }


    //Xóa truyện với id = i
    public int Delete(int i){
        SQLiteDatabase db = this.getReadableDatabase();

        int res = db.delete("truyen",ID_TRUYEN+" = "+i,null);
        return res;

    }
}
