package ru.tykvin.homework;

/**
 * Класс исключительно для удобства проверки работы.
 */
public class Help {
    public static final String text = "<style>body{ padding: 30 }</style>" +
            "Использование:<br>" +
            "/: получить эту страницу" +
            "/student [GET]: список студентов<br>" +
            "/student [POST]{first_name=&last_name=}: добавить студента<br>" +
            "/student/{id} [GET]: получить студента с {id}<br>" +
            "/student/{id} [DELETE]: удалить студента {id}<br>" +
            "/group [GET]: получить список групп со студентами<br>" +
            "/group [POST]{number=}: добаить группу с номером {number}<br>" +
            "/group/{id} [GET]: получить группу {id}<br>" +
            "/group/{id} [DELETE]: удалить группу {id}<br>" +
            "/group/{id}/student [GET]: список студентов группы с id {id}<br>" +
            "/group/{id}/student/{student_id} [PUT]: переместить студента {student_id} в группу {id}<br>" +
            "/group/{id}/student/{student_id} [DELETE]: убрать студента {student_id} из группы {id}<br>";
}
