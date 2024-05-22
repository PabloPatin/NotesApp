import {useState} from "react";

const CreateForm = ({categories = [], onAddNote}) => {
    const [note, setNote] = useState({
        title: '',
        category: '',
        content: '',
    })

    function handleChange(event) {
        const {name, value} = event.target;
        setNote(prevNote => {
            return {
                ...prevNote,
                [name]: value
            }
        })
    }
    function handleSubmit(event) {
        event.preventDefault();
        onAddNote(note);
        setNote({
            title: '',
            category: '',
            content: '',
        })
    }

    return (
        <div>
            <form className={"create-note"}>
                <input
                    name='title'
                    onChange={handleChange}
                    value={note.title}
                    placeholder={"Заголовок"}
                />
                <div className={"input-group"}>
                    <input
                        name='category'
                        onChange={handleChange}
                        value={note.category}
                        placeholder={"Категория"}
                    />
                    <select
                        name='category'
                        onChange={handleChange}
                        value={note.category}
                    >
                        <option value='' disabled>Выберите категорию</option>
                        {categories && categories.map((category) => (
                            <option
                                key={category.id}
                                value={category.title}
                            >
                                {category.title}
                            </option>
                        ))}
                    </select>
                </div>

                <textarea
                    name='content'
                    onChange={handleChange}
                    value={note.content}
                    placeholder={"Введите текст..."}
                />
                <button onClick={handleSubmit}>+</button>
            </form>
        </div>
    )
}

export default CreateForm;