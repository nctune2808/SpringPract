import React, { useCallback } from 'react';
import axios from 'axios';
import { useDropzone } from 'react-dropzone';

const PlayerCard = props => {


    const player = props.player;
    const category = props.category;

    const url = 'http://localhost:8080/api/v1/profile/'+player.profileId+'/image/download';

    return (
        <>
            {
                <div>
                    <img className="image"
                        src={url} alt="" />
                    <br />
                    <Dropzone player={player} />
                    <h1>{player.profileName}</h1>
                    <p>{player.profileId}</p>
                </div>
            }
        </>

    );

}

const Dropzone = props => {

    const player = props.player;

    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];
        console.log(file);
        const formData = new FormData();

        formData.append("file", file);
        axios.post(`http://localhost:8080/api/v1/profile/${player.profileId}/image/upload`,
            formData,
            {
                headers: {
                    "Content-type": "multipart/form-data"
                }
            }
        ).then(() => {
            console.log("File was uploaded successfully")
        }).catch(err => {
            console.log(err)
        });

    }, [player])
    const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop })

    return (
        <div {...getRootProps()}>
            <input {...getInputProps()} />
            {
                isDragActive ?
                    <p>Drop the files here ...</p> :
                    <p>Upload new</p>
            }
        </div>
    )
}

export default PlayerCard;